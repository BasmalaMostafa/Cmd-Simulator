import javafx.util.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {

    Termianl t = new Termianl();
    String []args;
    ArrayList<String> arr = new ArrayList<String>();
    String cmd;

    protected String valid(String Cmd)throws IOException, InterruptedException
    {
        if(Cmd.equals("clear") && arr.size() ==0 ) {
            t.clear();
        }

        else if(Cmd.equals("cd") && arr.size() ==1 ){
            t.cd(arr.get(0));
        }
        else if(Cmd.equals("cd") && arr.size() ==0 ){
            t.cd(" ");
        }
        else if(Cmd.equals("ls") && arr.size() ==0 ) // list files and directories whithout properties
        {
            return t.ls();
        }
        else if(Cmd.equals("cp") && arr.size() ==2 ) //copy from souّrce to dest
        {
            t.cp(arr.get(0),arr.get(1));
        }
        else if(Cmd.equals("mv") && arr.size() ==2 )//move from source to dest
        {
            t.mv(arr.get(0),arr.get(1));
        }
        else if(Cmd.equals("rm") && arr.size() ==1 )//remove file
        {
            t.rm(arr.get(0));
        }
        else if(Cmd.equals("mkdir") && arr.size() ==1 ) // make directory
        {
            t.mkdir(arr.get(0));
        }
        else if(Cmd.equals("rmdir") && arr.size() ==1 ) //remove directory if empty
        {
            t.rmdir(arr.get(0));
        }
        else if(Cmd.equals("cat") && arr.size() ==1 )// print content of file
        {

            return t.cat(arr.get(0));
        }
        else if(Cmd.equals("cat") && arr.size() ==2 )// print content of file
        {

            return t.cat(arr.get(0),arr.get(1));
        }
        else if(Cmd.equals("more") && arr.size() ==1 )//print more data
        {
            t.more(arr.get(0));
        }
        else if(Cmd.equals("pwd") && arr.size() ==0 )//Print your current working directory.
        {
            return t.pwd();
        }
        else if(Cmd.equals("date") && arr.size() ==0 )//Print your current working directory.
        {
            return t.date();
        }
        else if(Cmd.equals("args") && arr.size() ==0 )//Print your current working directory.
        {
            return t.args();
        }
        else if(Cmd.equals("exit") && arr.size() ==0 )//Print your current working directory.
        {
            t.exit();
        }
        else if(Cmd.equals("help") && arr.size() ==0 )//Print your current working directory.
        {
            return t.help();
        }
        else
            System.out.println("please enter valid command");
        return " ";

    }

    public String  Parser(String input)throws IOException, InterruptedException
    {
        arr.clear();
        int j = 0;

        for(int i=0;i<=input.length();i++)
        {
            if( j == 0 && ( i == input.length() || input.charAt(i) == ' ')  )
            {
                cmd = input.substring(j , i);
                //System.out.println(cmd);

                j = i+1;
            }
            else if( i == input.length() || input.charAt(i) == ' '  )
            {
                arr.add(input.substring(j , i));
                //System.out.println(input.substring(j , i));

                j = i+1;
            }
            else if(input.charAt(i) == '"' )
            {
				//cp "new txt.txt" "new txt.txt"

                arr.add(input.substring(i+1 , input.substring(i+1).indexOf('"') +  input.length() - input.substring(i+1).length()));
                //System.out.println(input.substring(i+1 , input.substring(i+1).indexOf('"') +  input.length() - input.substring(i+1).length()));
                j = input.substring(i+1).indexOf('"') +  input.length() - input.substring(i+1).length() + 2;
                i = j-1;
            }

        }


        return valid(cmd) ;

    }

}
