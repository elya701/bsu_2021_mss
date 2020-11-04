#include<stdio.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>
#include <getopt.h>
#include <unistd.h>
#include <string.h>

int main (int argc, char *argv[]){
    
    const char* short_options = "n:f:";
    char* text = "Hello World";
    char final_text[100] = "";
    int rez;
    int option_index = -1;
    int n = 1;
    int fd = 1;
    
    const struct option long_options[] = {
        {"count",optional_argument,NULL,'n'},
        {"fd",required_argument,NULL,'f'},
        {NULL,0,NULL,0}
    };
    
    if (argc > 1) {
        if (strcmp(argv[1], "-n") == 0 || strcmp(argv[1], "--fd") == 0 || strcmp(argv[1], "-f") == 0 || strcmp(argv[1], "--f") == 0) {
        } else {
            text = argv[1];
        }
    }
    
    while ((rez=getopt_long(argc,argv,short_options,
                            long_options,&option_index))!=-1){
        
        switch(rez){
            case 'n': {
                if (optarg!=NULL) {
                    //printf("found size with value %s\n",optarg);
                    n = atoi(optarg);
                } else {
                    //printf("found size without value\n");
                }
                break;
            };
                
            case 'f': {
                if (optarg!=NULL) {
                    //printf("file descriptor = %s\n",optarg);
                    fd = open(optarg, O_WRONLY | O_CREAT | O_TRUNC, 0644);
                    if (fd < 0) { perror("r1"); exit(1); }
                    printf("opened the fd = %d\n", fd);
                } else {
                    //printf("file descriptor error");
                }
                break;
            };
            case '?': default: {
                // printf("found unknown option\n");
                break;
            };
        };
        option_index = -1;
    };
    
    for (int i = 0; i < n; i++) {
        if (strcmp(text, "") == 0) {
            sprintf(final_text, "%s %s", final_text, text);
        } else {
            
            if (strcmp(final_text, "") == 0) {
                sprintf(final_text, "%s%s", final_text, text);
            } else {
                sprintf(final_text, "%s %s", final_text, text);
            }
        }
    }
    
    int sz = write(fd, final_text, strlen(final_text));
    if (close(fd) < 0) { perror("r1"); exit(1); }
    printf("closed the fd.\n");
    return 0;
}
