module Demo
{
    struct Vector
    {
        int x;
        int y;
    };

    sequence<Vector> Vectors2D;

    interface Calc
    {
        long add(int a, int b);
        double norm(Vector vector);
        Vector addVectors(Vectors2D vectors);
    };
};
