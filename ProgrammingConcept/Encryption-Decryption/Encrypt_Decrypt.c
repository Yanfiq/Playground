#include <stdio.h>
#include <ctype.h>
#include <stdlib.h>

char input, importDir[260], exportDir[260];
enum option{encrypt=1, decrypt};
char alphabet_lowercase[26][1] = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
char alphabet_uppercase[26][1] = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
int key, choice;

void encrypt_decrypt(FILE *import, FILE *export, int key, int choice)
{
    while((input = fgetc(import))!=EOF)
    {
        if(isalpha(input)==0)
        {
            fprintf(export, "%c", input);
        }
        else
        {
            for(int j=0; j<26; j++)
            {
                if(input == alphabet_lowercase[j][0] && islower(input))
                {
                    if(choice == encrypt)
                    {
                        fprintf(export, "%c", alphabet_lowercase[(j+key)%26][0]);
                    }
                    else if(choice == decrypt)
                    {
                        fprintf(export, "%c", alphabet_lowercase[(j-(key%26)+26)%26][0]);
                    }
                    break;
                }
                else if(input == alphabet_uppercase[j][0] && isupper(input))
                {
                    if(choice == encrypt)
                    {
                        fprintf(export, "%c", alphabet_uppercase[(j+key)%26][0]);
                    }
                    else if(choice == decrypt)
                    {
                        fprintf(export, "%c", alphabet_uppercase[(j-(key%26)+26)%26][0]);
                    }
                    break;
                }
            }
        }
    }
}

int main()
{
    restart:
    printf("================= WELCOME TO CAESAR'S CIPHER ENCRYPTOR AND DECRYPTOR =================\n");
    printf("Enter the directory and destination of text file that you wish to encrypt or decrypt\n");
    printf("Example                      : D:\\algorithm\\codes\\secret.txt\n");
    printf("Directory                    : "); scanf("%[^\n]", importDir); while(getchar() != '\n');
    printf("Destination                  : "); scanf("%[^\n]", exportDir); while(getchar() != '\n');
    printf("Enter the key for encryption : "); scanf("%d", &key     );
    printf("1. Encrypt\n2. Decrypt\nWhat do you want to do       : "); scanf("%d", &choice);  if(choice<1||choice>2 ){printf("There is no such choice");goto exit;}
    FILE *import, *export;
    import = fopen(importDir, "r");
    export = fopen(exportDir, "w");
    if(import != NULL && export != NULL && choice == 1)
    {
        encrypt_decrypt(import, export, key, encrypt);
        printf("The file has been encrypted successfully. Remember, the key is %d", key);
    }
    else if(import != NULL && export != NULL && choice == 2)
    {
        encrypt_decrypt(import, export, key, decrypt);
        printf("The file has been decrypted successfully with %d as the key", key);
    }
    else
    {
        printf("The file was not found :(");
    }
    fclose(import); fclose(export);
    exit:
    while(getchar() != '\n');
    printf("\nDo you want to restart the program (Y/N) : "); input = getc(stdin);
    if(input == 'Y' || input == 'y')
    {
        system("cls");
        while(getchar() != '\n');
        goto restart;
    }
    else
    {
        printf("\nThank you for using this program");
    }
}