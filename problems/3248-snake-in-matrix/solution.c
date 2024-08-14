int finalPositionOfSnake(int n, char** commands, int commandsSize) {
    int v=0;
    int num[n][n];
    int i=0,j=0;

    for(int x=0;x<n;x++){
        for(int y=0;y<n;y++){
            num[x][y]=v;
            v+=1;
        }
    }

    for(int k=0;k<commandsSize;k++){
        char c=commands[k][0];
 
        if(c=='U'){
            i-=1;
        }
        else if(c=='D'){
            i+=1;
        }
        else if(c=='R'){
            j+=1;
        }
        else if(c=='L'){
            j-=1;
        }
    }
    int res=num[i][j];

    return res;
}