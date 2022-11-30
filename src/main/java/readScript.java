import java.io.*;
import java.util.*;

public class readScript {
    public static void main(String[] args) throws IOException {
        try {
            File file = new File("src/Script.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            ArrayList<String> sentence = new ArrayList<>();
            ArrayList<String> mixed_sentence = new ArrayList<>();
            String temp = "";
            boolean first_drop = true;
            int change_number1 = 2;
            int change_number2 = 1;
            int cnt = 0;
            boolean first_drop_2 = true;

            while ((temp = br.readLine()) != null) {
                if(first_drop) {
                    first_drop = false;
                    continue;
                }

                String temp1 = Integer.toString(change_number1);
                String temp2 = Integer.toString(change_number2);

                sentence.add(temp.replaceFirst(temp1, temp2));

                change_number1++;
                change_number2++;

            }

            int[] randomNumbers = new int[sentence.size()];

            for( int i = 0 ; i < randomNumbers.length ; i++ ){
                randomNumbers[i] = (int)(Math.random() * (sentence.size()) + 1);

                if(i != 0 ){
                    for( int j = i-1 ; j>= 0 ; j-- ){
                        if( randomNumbers[i] == randomNumbers[j]){
                            i--;
                            continue;
                        }
                    }
                }
            }

            int[] answer_1 = new int[sentence.size()];
            int[] answer_2 = new int[sentence.size()];


            HashMap<Integer, Integer> answer = new HashMap<>();

            for(int i=0; i<sentence.size();i++) {
                String temp_sentence = " ";
                temp_sentence = sentence.get(randomNumbers[i]-1);
                String number2 = Integer.toString(i+1);
                answer_1[i] = randomNumbers[i];
                answer_2[i] = i+1;
                answer.put(answer_1[i],answer_2[i]);
                mixed_sentence.add(temp_sentence.replaceFirst("1", number2));
            }
            for(int i = 0 ;i < sentence.size();i++) {
                System.out.println(mixed_sentence.get(i)+" ");
            }
            System.out.println();

            String write_braket ="";
            for(int i=0;i<sentence.size();i++) {

                if(i == sentence.size()-1) {
                    write_braket = write_braket+"(  )";
                    break;
                }

                write_braket = write_braket+"(  )->";
            }

            System.out.println(write_braket);
            System.out.println();



            Iterator<Integer> iteratorKey = answer.keySet().iterator( );

            while(iteratorKey.hasNext()) {
                int key = iteratorKey.next();
                if(first_drop_2) {
                    first_drop_2 = false;
                    System.out.print(""+answer.get(key));
                    continue;
                }
                System.out.print(" -> "+answer.get(key));
                cnt = cnt+1 ;
            }

        }
        catch(FileNotFoundException e) {
            System.out.println(e);
        }
    }
}
