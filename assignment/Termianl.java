import java.io.IOException;
import java.nio.file.*;
import java.util.Scanner;
import java.io.File;
import java.util.Date;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Termianl {

    static private String def_Path  = "D://";

    Termianl()
    {
        System.setProperty("user.dir",def_Path);
    }

    public  boolean isValidPath(String path) {
        try {
            Paths.get(path);
        } catch (InvalidPathException | NullPointerException ex) {
            return false;
        }
        return true;
    }

    public void clear() throws IOException, InterruptedException
    {
        for(int i = 0;i<100;i++)
            System.out.println();
    }

    public void cd (String path)
    {
        if(path.equals(" "))
        {
            System.setProperty("user.dir",def_Path);
            return;
        }

        if(isValidPath(path))
        {
            File f = new File(path);
            if(f.exists())
            {
                System.setProperty("user.dir",path);
            }
            else {
                throw new IllegalArgumentException("this path dosen't found");
            }
        }
        else {
            throw new IllegalArgumentException("this path dosen't correct");
        }
    }

    public String ls ()
    {
        File folder = new File(System.getProperty("user.dir"));
        File[] listOfFiles = folder.listFiles();
        if(!folder.exists())
        {
            throw new IllegalArgumentException("File or direction don't exist");
        }

        for (File listOfFile : listOfFiles) {
            if (listOfFile.isFile()) {
                System.out.println("File " + listOfFile.getName());
            } else if (listOfFile.isDirectory()) {
                System.out.println("Directory " + listOfFile.getName());
            }
        }
        return listOfFiles.toString();
    }

    public void  cp (String source , String destination)throws IOException
    {

        File so = new File(source);
        File de = new File(destination);
        Files.copy(so.toPath(), de.toPath());
    }

    public void  mv (String source , String destination)throws IOException
    {

        File so = new File(source);
        File de = new File(destination);
        Files.move(so.toPath(), de.toPath());
    }

    public void  rm (String source )throws IOException
    {
        File so = new File(source).getAbsoluteFile();
        Files.delete(so.toPath());
    }

    public void  mkdir (String source )throws IOException
    {
        File so = new File(source);
        Files.createDirectory(so.toPath());

    }

    public void  rmdir (String source )throws IOException
    {
        File so = new File(source);
        Files.deleteIfExists(so.toPath());

    }

    public String cat (String source)throws IOException
    {
        String content ;
       // if(source.charAt(1) != ':')
           // source = System.getProperty("user.dir") + source;
        File file=new File(source).getAbsoluteFile();
        content = new Scanner(file).useDelimiter("\\Z").next();
        System.out.println(content);
        return content;
    }

    public String  cat (String source1 , String source2 )throws IOException
    {
        return cat(source1)+cat(source2);
    }

    public void  more (String source )throws IOException
    {
        File so = new File(source).getAbsoluteFile();
        String line = null;
        if(so.exists())
        {
//            FileReader fileReader = new FileReader(source);
//
//            // Always wrap FileReader in BufferedReader.
//            BufferedReader bufferedReader =new BufferedReader(fileReader);

            Scanner in = new Scanner(System.in);
            Scanner read = new Scanner (so);
            int input = 1;
            while(input == 1) {
                int x = 0;
                while (read.hasNextLine()/* bufferedReader.readLine()) != null*/ && x < 10) {
                    line = read.nextLine();
                	System.out.println(line);
                    x++;
                }
                if( !read.hasNextLine()/*line = bufferedReader.readLine()) == null*/ )
                    break;
                input = in.nextInt();
            }
        }
        else
        {
            throw new IllegalArgumentException("can't find this file");
        }
    }

    public String  pwd ()throws IOException
    {
        System.out.println(System.getProperty("user.dir"));
        return System.getProperty("user.dir");
    }

    public String date() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        return date.toString();
    }


    public String args() {
        String out = "";
        out+="cd      >>  The distination directory i.e. (/E)\n";
        out+="rm      >>  The file to delete i.e. (/E/file.txt)\n";
        out+="mkdir   >>  The path and name of directory to creat i.e. (/E/file)\n";
        out+="rmdir   >>  The path and name of directory to remove i.e. (/E/file)\n";
        out+="cp      >>  The path and name of file to copy and the copy distination directory path i.e. (/E/file /F/file)\n";
        out+="mv      >>  The path and name of file to move and distination directory path i.e. (/E/file /F/file)\n";
        out+="more    >>  The path and name of the file to diplay i.e (/E/file.txt)\n";
        out+="cat     >>  The path and name of the file to diplay i.e (/E/file.txt)\n";
        out+="clear   >>  No parametrs\n";
        out+="pwd     >>  No parametrs\n";
        out+="ls      >>  No parametrs\n";
        out+="help    >>  No parametrs\n";
        out+="date    >>  No parametrs\n";
        out+="args    >>  No parametrs\n";
        out+="exit    >>  No parametrs\n";
        System.out.println(out);
        return  out;
    }

    public String help() {
        String out = "";
        out+="cd      >>  Change the current directory to a specific directory  \n";
        out+="rm      >>  Remove specific file from specific directory \n ";
        out+="mkdir   >>  Creating a directory \n ";
        out+="rmdir   >>  Removing a directory \n ";
        out+="cp      >>  Copying  specific folder or directory to a specific directory  \n";
        out+="mv      >>  Moving  specific folder or directory to a specific directory  \n";
        out+="clear   >>  clear the screen  \n";
        out+="pwd     >>  Display the currnt directory \n ";
        out+="ls      >>  Display the folders and files in the currnt directory  \n";
        out+="help    >>   Display the function of all commands \n ";
        out+="date    >>  Display the current date and time ...Current date/time \n";
        out+="args    >>  Dispaly the command's prameters ...List all command arguments\n" ;
        out+="exit    >>  Exiting the CLI...Stop all  \n";
        out+="more    >>  Reading a number of lines from a file  \n";
        out+="cat     >>  Display the content of a file \n ";
        System.out.println(out);
        return out;
    }

    public void exit() {System.exit(0);}


    public static void  main(String[] args)throws IOException, InterruptedException
    {

        Parser p = new Parser();
        while (true) {
            try {
                Scanner input = new Scanner(System.in);
                String line = input.nextLine();
                String[] clis=line.split("[|]"); /*split the line if it has | to several command lines*/

                for(int i=0 ; i< clis.length ; i++) {
                    String str = new String(clis[i]) , filename = " ";
                    int z = 0;
                    if(clis[i].contains(">>"))
                    {
                        int x = clis[i].indexOf('>');
                        str = clis[i].substring(0,x);
                        filename = clis[i].substring(x+2);
                        //append  in file in filename
                        z = 2;
                    }
                    else if(clis[i].contains(">"))
                    {
                        int x = clis[i].indexOf('>');
                        str = clis[i].substring(0,x);
                        filename = clis[i].substring(x+1);
                        //over write in file infilename
                        z = 1;
                    }
                    String outtofile =  p.Parser(str);
                    if(z == 1 )
                    {
                        Files.write(Paths.get(filename), outtofile.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
                    }
                    else if(z == 2)
                    {
                        Files.write(Paths.get(filename), outtofile.getBytes(), StandardOpenOption.APPEND);
                    }

                }
            }
            catch (IllegalArgumentException e)
            {
                System.out.println(e.getMessage());
            }
            catch ( IOException ee) {
                System.out.println(ee.getMessage());
            }
            catch (Exception eee)
            {
                System.out.println("enter valid command: " + eee.getMessage());
            }
        }
    }

}
