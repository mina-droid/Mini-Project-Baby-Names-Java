import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;

public class MiniProject1BabyNames {
    public void totalBirths(FileResource fr)
    {
        int totalBirth = 0;
        int totalB = 0;
        int totalG = 0;
        int bNum = 0;
        int gNum = 0;
        int totalNames = 0;
        for (CSVRecord rec : fr.getCSVParser(false))
        {
            int total1 = Integer.parseInt(rec.get(2));
            totalBirth += total1;
            totalNames++;
            if (rec.get(1).equals("M"))
            {
                totalB += total1;
                bNum++;
            }
            else
            {
                totalG += total1;
                gNum++;
            }
            
        }
        
        System.out.println("total Boys  =  " + totalB);
        System.out.println("total Girls  =  " + totalG);
        System.out.println("total Boys names =  " + bNum);
        System.out.println("total Girls names =  " + gNum);
        System.out.println("total names =  " + totalNames);
    }
    
    public void test1()
    {
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
    
    public int getRank(int year, String name, String gender)
    {
        String fname = "yob" + year + ".csv";
        FileResource fr = new FileResource(fname);
        int rank = 0;
        CSVParser parser = fr.getCSVParser(false);
        for (CSVRecord rec : parser)
        {
            if(rec.get(1).equals(gender))
            {
                rank++;
                if(rec.get(0).equals(name))
                {
                    return rank;
                }
            }
            
        }
        return -1;
    }
    
    public void test2()
    {
        int rank = getRank(1971, "Frank","M");
        System.out.println(rank);
    }
    
    public String getName(int year, int rank, String gender)
    {
        String name = "NO NAME";
        String fname = "yob" + year + ".csv";
        FileResource fr = new FileResource(fname);
        CSVParser parser = fr.getCSVParser(false);
        int count = 0;
           for (CSVRecord rec : parser)
        {
            if(rec.get(1).equals(gender))
            {
                count++;
                if(count == rank)
                {
                    return rec.get(0);
                }
            }
            
        }
        return name;
    }
    
    public void test3()
    {
        String name = getName(1982, 450,"M");
        System.out.println(name);
    }
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender)
    {
        int rank = getRank(year, name, gender);
        if (rank != -1)
        {
        String fname = getName(year, rank, gender);
        String sname = getName(newYear, rank, gender);
        System.out.println(fname + " born in  " + year + " would be " + sname + " if he/she was born in " + newYear);
        }
        else
        {
            System.out.println("No Name");
        }
    }
    
    public void test4()
    {
        whatIsNameInYear("Owen", 1974, 2014, "M");
    }
    
    public int yearOfHighestRank(String name, String gender)
    {
        DirectoryResource dr = new DirectoryResource();
        int year = -1;
        int maxrank = 1000000000;
        for(File f : dr.selectedFiles())
        {
            FileResource fr = new FileResource(f);
            String filename = f.getName();
            int start = filename.indexOf("b");
            String yearnum = filename.substring(start + 1, start + 5);
            int fyear = Integer.parseInt(yearnum);
            int rank = getRank(fyear,name,gender);
            if(rank != -1)
            {
            if ( rank < maxrank)
            {
                maxrank = rank;
                year = fyear;
            }
            }
            
            
        }
        return year;
    }
    
    public void test5()
    {
        int year = yearOfHighestRank("Genevieve", "F");
        System.out.println(year);
    }
    
    public double getAverageRank(String name, String gender)
    {
                DirectoryResource dr = new DirectoryResource();
                double count = 0;
                double sum = 0;
                double avg = 0;
                for(File f : dr.selectedFiles())
        {
            FileResource fr = new FileResource(f);
            String filename = f.getName();
            int start = filename.indexOf("b");
            String yearnum = filename.substring(start + 1, start + 5);
            int fyear = Integer.parseInt(yearnum);
            int rank = getRank(fyear,name,gender);
            if(rank != -1)
            {
                count++;
                sum += rank;
                
            }
            
            
            
        }
        if (count == 0)
        {
            return -1.0;
            
        }
        avg = sum /  count;
        return avg;
        
    }
    
    public void test6()
    {
        double avg = getAverageRank("Robert" , "M");
        System.out.println(avg);
    }
    
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender)
    {   String fname = "yob" + year + ".csv";
        FileResource fr = new FileResource(fname);
        CSVParser parser = fr.getCSVParser(false);
        int frank = getRank(year , name, gender);
        System.out.println(frank);
        int total = 0;
            for (CSVRecord rec : parser)
        {
            if(rec.get(1).equals(gender))
            {
                System.out.println(year);
                System.out.println(rec.get(0));
                System.out.println(rec.get(1));
                int rank = getRank(year , rec.get(0), rec.get(1));
                
                if (rank < frank)
                {
                    int totalB = Integer.parseInt(rec.get(2));
                    total += totalB;
                }
            }
            
        }
        return total;
    }
    
    
    public void test7 ()
    {
        int total = getTotalBirthsRankedHigher(1990, "Drew" , "M");
        System.out.println(total);
    }
    
    
}
