package org.example;

import org.apache.zookeeper.*;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class ZookeeperApp {
    private static DefaultMutableTreeNode rootNode;
    private static JFrame frame;
    private static ZooKeeper zooKeeper;
    private static final Watcher watcher = event -> {
        String znode = "/a";
        if (event.getType() == Watcher.Event.EventType.NodeCreated && event.getPath().equals(znode)) {
            System.out.println("nowe A");
            try {
                startExternalApplication();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (event.getType() == Watcher.Event.EventType.NodeDeleted && event.getPath().equals(znode)) {
            System.out.println("usuniete A");
            try {
                stopExternalApplication();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (event.getType() == Watcher.Event.EventType.NodeChildrenChanged && event.getPath().equals(znode)) {

            JFrame frame = new JFrame("Child Added");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            int childrenCount = 0;
            try {
                childrenCount = zooKeeper.getChildren(znode, false).size();
            } catch (KeeperException | InterruptedException e) {
                throw new RuntimeException(e);
            }

            // Tworzenie etykiety z liczbą dzieci
            JLabel label = new JLabel("Number of children: " + childrenCount);
            frame.setSize(300, 100);
            frame.getContentPane().add(label);
            frame.setVisible(true);
        }
        try {
            zooKeeper.exists(znode, true);
            zooKeeper.getChildren(znode, true);
        } catch (KeeperException | InterruptedException e) {

        }
    };

    private static void startExternalApplication() throws IOException {
        Runtime.getRuntime().exec("notepad.exe");
    }

    private static void stopExternalApplication() throws IOException {
        Runtime.getRuntime().exec("taskkill /F /IM notepad.exe");
    }

    private static void populateNode(ZooKeeper zooKeeper, DefaultMutableTreeNode parentNode, String path) throws KeeperException, InterruptedException {
        List<String> children = zooKeeper.getChildren(path, false);
        for (String child : children) {
            String childPath = path.equals("/") ? "/" + child : path + "/" + child;
            DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(child);
            parentNode.add(childNode);
            populateNode(zooKeeper, childNode, childPath);
        }
    }

    private static void createAndShowGUI(DefaultMutableTreeNode rootNode) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Zookeeper Node Tree");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            JTree tree = new JTree(rootNode);

            JScrollPane scrollPane = new JScrollPane(tree);
            frame.add(scrollPane);

            frame.setSize(300, 400);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        String hostPort = "localhost:2181";
        zooKeeper = new ZooKeeper(hostPort, 3000, watcher);


        while (true) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String line;
                {
                    line = reader.readLine();
                    if (line.equalsIgnoreCase("1")) {
                        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("/");
                        try {
                            populateNode(zooKeeper, rootNode, "/a");
                        } catch (KeeperException | InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        createAndShowGUI(rootNode);
                }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
