/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haikufinder;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Homework to find haiku's in a text file.
 * @author cslade7808
 */
public class HaikuFinder {

    private String sentence1, sentence2, sentence3;
    private File text;
    private int hCount;
    
    public HaikuFinder(File f)
    {
        hCount = 0;
        text = f;
        sentence1 = "";
        sentence2 = "";
        sentence3 = "";
        for(sentence1 = this.findNextSentence(); sentence1 != null; sentence1 = this.findNextSentence())
        {
            this.findHaiku();
        }
        if(hCount == 0)
        {
            System.out.println("No haiku's could be found in this file...");
        }else{System.out.println("There are " + hCount + " haiku's in the file.");}
    }
    
    private String findNextSentence()
    {
        BufferedReader br;
        String temp;
        try{
            br = new BufferedReader(new FileReader(this.text));
            temp = "";
        if((temp = br.readLine()) != null)
        {
            return temp;
        }
        br.close();
        }catch(Exception e)
        {
            System.err.println(e);
        }
        
        return null;
    }
    
    private int countSyllables()
    {
        int count = 0;
        sentence1 = sentence1.toLowerCase();
        for(int i = 0; i < sentence1.length(); i++)
        {
            if((i<sentence1.length()-1) && (sentence1.charAt(i) == 'a' || sentence1.charAt(i) == 'e' ||
                    sentence1.charAt(i) == ('i') || sentence1.charAt(i) == 'o' || 
                    sentence1.charAt(i) == 'u' || sentence1.charAt(i) == 'y') && (sentence1.charAt(i+1) == 'a' || 
                    sentence1.charAt(i+1) == 'e' ||
                    sentence1.charAt(i+1) == ('i') || sentence1.charAt(i+1) == 'o' || 
                    sentence1.charAt(i+1) == 'u' || sentence1.charAt(i+1) == 'y'))
            {
                count++;
                i++;
            }if((i<sentence1.length()-1) && sentence1.charAt(i) == 'e' && sentence1.charAt(i+1) == ' ')
            {
                count--;
            }if(sentence1.charAt(i) == 'a' || sentence1.charAt(i) == 'e' ||
                    sentence1.charAt(i) == ('i') || sentence1.charAt(i) == 'o' || 
                    sentence1.charAt(i) == 'u' || sentence1.charAt(i) == 'y')
            {
                count++;
            }
        }
        return count;
    }
    
    private Boolean findHaiku()
    {
        if(this.countSyllables() == 5)
        {
            sentence3 = sentence1;
            sentence1 = findNextSentence();
        }else{return false;}
        if(this.countSyllables() == 7)
        {
            sentence2 = sentence1;
            sentence1 = findNextSentence();
        }else{return false;}
        if(this.countSyllables() == 5)
        {
            System.out.println(sentence3 + "\n" + sentence2 + "\n" + sentence1);
            hCount++;
            return true;
        }else{
            return false;
        }
    }
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner keypad = new Scanner(System.in);
        System.out.println("Please enter the file path of the file to be checked for haiku's: ");
        File tempFile = new File(keypad.nextLine());
        HaikuFinder haiFi = new HaikuFinder(tempFile);
        keypad.close();
    }
    
}