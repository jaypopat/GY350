#include <stdio.h>

int main() {
    int choice;
    float s, u, v, a, t;

    printf("What would you like to calculate?\n");
    printf("1. Displacement\n");
    printf("2. Initial velocity\n");
    printf("3. Velocity\n");
    printf("4. Acceleration\n");
    printf("5. Time\n");
    scanf("%d", &choice);
    switch (choice) {
        case 1: // Displacement
            printf("Enter initial velocity (u): ");
            scanf("%f", &u);
            printf("Enter acceleration (a): ");
            scanf("%f", &a);
            printf("Enter time (t): ");
            scanf("%f", &t);
            s = u*t + 0.5*a*t*t;
            printf("Displacement (s) = %f\n", s);
            break;
        case 2: // Initial velocity
            printf("Enter displacement (s): ");
            scanf("%f", &s);
            printf("Enter acceleration (a): ");
            scanf("%f", &a);
            printf("Enter time (t): ");
            scanf("%f", &t);
            u = (s - 0.5*a*t*t) / t;
            printf("Initial velocity (u) = %f\n", u);
            break;
        case 3: // Velocity
            printf("Enter initial velocity (u): ");
            scanf("%f", &u);
            printf("Enter acceleration (a): ");
            scanf("%f", &a);
            printf("Enter time (t): ");
            scanf("%f", &t);
            v = u + a*t;
            printf("Velocity (v) = %f\n", v);
            break;
        case 4: // Acceleration
            printf("Enter initial velocity (u): ");
            scanf("%f", &u);
            printf("Enter final velocity (v): ");
            scanf("%f", &v);
            printf("Enter time (t): ");
            scanf("%f", &t);
            a = (v - u) / t;
            printf("Acceleration (a) = %f\n", a);
            break;
        case 5: // Time
            printf("Enter initial velocity (u): ");
            scanf("%f", &u);
            printf("Enter final velocity (v): ");
            scanf("%f", &v);
            printf("Enter acceleration (a): ");
            scanf("%f", &a);
            t = (v - u) / a;
            printf("Time (t) = %f\n", t);
            break;
        default:
            printf("Invalid choice.\n");
            break;
    }
    return 0;
}
