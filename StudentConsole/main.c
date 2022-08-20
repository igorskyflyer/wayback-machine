#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct Student {
 char name[20];
 char surname[30];
 char course[3];
 int index;
 int year;  
} Student;

int main() {
 Student *Students;
 int option, n, i, indx, courseStudents=0, foundStudent=0;
 char crs[3];
 
 printf("Unesite broj studenata (1-300): ");
 scanf("%d", &n);
 printf("\n");
 
 if(n<1 || n>300) {
  printf("Niste uneli validan broj studenata!\nPritisnite bilo koji taster, za prekid programa.\n\n");
  system("PAUSE");
  exit(0);
 }
 
 Students=(Student*)malloc(n*sizeof(Student));
 printf("Unesite podatke o studentima:\n");
 
 for(i=0; i<n; i++) {
  printf("\nPodaci o %d. studentu:\n", i+1);
  printf("Ime: ");
  scanf("%s", (Students+i)->name);
  printf("Prezime: ");
  scanf("%s", (Students+i)->surname);
  printf("Smer: ");
  scanf("%s", (Students+i)->course);
  printf("Broj indeksa: ");
  scanf("%d", &(Students+i)->index);
  printf("Godina upisa: ");
  scanf("%d", &(Students+i)->year); }
    
 printf("\nPodaci su uspesno dodati!\n\n");
 printf("Pritisnite bilo koji taster za prikaz opcija.\n");
 system("PAUSE");
 system("CLS");
 
 printf("\n- OPCIJE -\n\n");
 printf("1. Prikazivanje podataka o svim studentima\n");
 printf("2. Prikazivanje podataka o studentima odredjenog smera\n");
 printf("2. Prikazivanje podataka o odredjenom studentu\n");
 printf("\nVas izbor je: ");
 scanf("%d", &option);
 printf("\n");
 
 if(option<1 || option>3) {
  printf("Doslo je do greske. Program ce sada prekinuti sa radom.");
  exit(0);           
 }
 
 switch(option) {
  case 1:
   printf("PODACI O SVIM STUDENTIMA\n\n");
   for(i=0; i<n; i++)
    printf("STUDENT #%d\nIme: %s\nPrezime: %s\nSmer: %s\nBroj indeksa: %d\nGodina upisa: %d\n\n", i+1, (Students+i)->name, (Students+i)->surname, (Students+i)->course, (Students+i)->index, (Students+i)->year);
   system("PAUSE");
  break;
  case 2:
   printf("Unesite smer: ");
   scanf("%s", &crs);
   printf("\nPODACI O STUDENTIMA %s SMERA\n", crs);
   
   for(i=0; i<n; i++) {
    if(strcmp(crs, (Students+i)->course)==0) {
     courseStudents++; 
     printf("STUDENT #%d\nIme: %s\nPrezime: %s\nSmer: %s\nBroj indeksa: %d\nGodina upisa: %d\n\n", i+1, (Students+i)->name, (Students+i)->surname, (Students+i)->course, (Students+i)->index, (Students+i)->year); }
   }
   
    if(courseStudents<1)
     printf("Nema studenata na %s smeru.\n\n", crs); 
   
   system("PAUSE");
  break;
  case 3:
   printf("Unesite broj indeksa studenta: ");
   scanf("%d", &indx);
   printf("\n");
   for(i=0; i<n; i++) {
    if(indx==(Students+i)->index) {
     foundStudent++;
     printf("Ime: %s\nPrezime: %s\nSmer: %s\nBroj indeksa: %d\nGodina upisa: %d\n", (Students+i)->name, (Students+i)->surname, (Students+i)->course, (Students+i)->index, (Students+i)->year); }
   }  
    if(foundStudent<1)
     printf("Nije pronadjen student sa brojem indeksa: %d.\n\n", indx);
            
   system("PAUSE");
  break;               
 }
 
 free(Students);
 return 0;	
}
