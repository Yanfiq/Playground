#include <stdio.h>
#include <string.h>

int looping()
{
    int menu = 0;
    printf("1.mengenkripsi data\n"
           "2.mendekripsi kata\n"
           "3.mengakhiri program\n"
           "pilihan:");
    scanf("%d", &menu);
    return menu;
}

int main()
{
    int a;
    FILE *B;
    FILE *D;
    D = fopen("quizencrypted.txt", "w");
    char c;
    int e;
    int key;

    while ((a = looping()) != 3)
    {
        switch (a)
        {
        case 1:
            if ((B = fopen("QuizSDA.txt", "r")) == NULL)
            {
                printf("file tidak tersedia");
            }
            else
            {
                printf("Enkripsi\n");
                printf("masukkan key untuk enkripsinya:");
                scanf("%d", &key);
                while ((c = fgetc(B)) != EOF)
                {
                    if (c == ' ')
                    {
                        e = ' ';
                    }
                    else if (c >= 'A' && c <= 'Z')
                    {
                        e = ((c + key - 'A') % 26) + 'A';
                    }
                    else if (c >= 'a' && c <= 'z')
                    {
                        e = ((c + key - 'a') % 26) + 'a';
                    }
                    fprintf(D,"%c", e);
                }
            }
            fclose(D);
            fclose(B);
            break;

        case 2:
            if ((B = fopen("Quiz SDA", "a+")) == NULL)
            {
                printf("file tidak tersedia");
            }
            else
            {
                printf("Dekripsi\n");
                printf("masukkan key untuk dekripsinya:");
                scanf("%d", &key);
                while ((c = fgetc(B)) != EOF)
                {
                    if (c == ' ')
                    {
                        e = ' ';
                    }
                    else if (c >= 'A' && c <= 'Z')
                    {
                        e = ((c - key - 'A' + 26) % 26) + 'A';
                    }
                    else if (c >= 'a' && c <= 'z')
                    {
                        e = ((c - key - 'a' + 26) % 26) + 'a';
                    }
                    fprintf(D,"%c", e);
                }
            }
            fclose(D);
            fclose(B);
            break;

        default:
            printf("Pilihan tidak valid\n");
            break;
        }
    }
    return 0;
}
