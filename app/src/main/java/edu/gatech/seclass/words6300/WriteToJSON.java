package edu.gatech.seclass.words6300;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;


public class WriteToJSON {
    static class MyComparator implements Comparator<Map.Entry<String, Integer>> {
        @Override
        public int compare(Map.Entry<String, Integer> m1, Map.Entry<String, Integer> m2) {
            if (m1.getValue() == m2.getValue()) {
                return 0;
            }
            return m1.getValue() > m2.getValue() ? -1 : 1;
        }
    }

    private static String FILE_NAME = "ScoreHistory.json";
    private static FileReader fileReader = null;
    private static FileWriter fileWriter = null;
    private static BufferedReader buffferedReader = null;
    private static BufferedWriter bufferedWriter =null;
    private static String response = null;
    private static DataHolder dataHolder = DataHolder.getInstance();
    private static File file = new File(dataHolder.getCurrDirectory(), FILE_NAME);
    private static final String TAG = "MyActivity";

    public static void WriteJSON(String name, int inputScore) {
        String score = Integer.toString(inputScore);

        try{
            StringBuilder output = new StringBuilder();
            fileReader = new FileReader(file.getAbsoluteFile());
            buffferedReader = new BufferedReader(fileReader);
            String line = "";

            while((line = buffferedReader.readLine()) != null) {
                output.append(line + "\n");
            }
            response = output.toString();
            buffferedReader.close();

            JSONObject messageDetails = new JSONObject(response);
            Boolean isUserExisting = messageDetails.has(name);
            if (!isUserExisting) {
                JSONArray newUserMessage = new JSONArray();

                    JSONObject storeScore = new JSONObject();
                    storeScore.put("score", inputScore);

                    JSONObject storeSettings = new JSONObject();

                        JSONArray alphabetScore = new JSONArray();

                            JSONObject letterPairA = new JSONObject();
                            letterPairA.put("A", 1);
                            alphabetScore.put(letterPairA);
                            JSONObject letterPairB = new JSONObject();
                            letterPairB.put("B", 2);
                            alphabetScore.put(letterPairB);
                            JSONObject letterPairC = new JSONObject();
                            letterPairC.put("C", 3);
                            alphabetScore.put(letterPairC);

                    storeScore.put("settings", alphabetScore);

                newUserMessage.put(storeScore);
                Log.v(TAG, " newbee : " + newUserMessage.toString());


                messageDetails.put(name, newUserMessage);
            } else {
                JSONArray userMessage = (JSONArray) messageDetails.get(name);
                Log.v(TAG, " newbee1 : " + messageDetails.toString());
                JSONObject scoreObject = (JSONObject) userMessage.get(0);
                Log.v(TAG, " newbee2 : " + messageDetails.toString());
                int oldScore = (Integer) scoreObject.get("score");
                JSONArray letterFreq = (JSONArray) scoreObject.get("settings");
                Log.v(TAG, " newbee3 : " + messageDetails.toString());
                if (oldScore < inputScore) {
                    scoreObject.put("score", inputScore);
                    ((JSONObject) letterFreq.get(0)).put("A", 10);
                }
                Log.v(TAG, " newbee4 : " + messageDetails.toString());
            }
            fileWriter = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fileWriter);
            bw.write(messageDetails.toString());
            bw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {

        } catch (JSONException e) {

        }
    }

    public static String WriteUserIDJSON(String name) {
        String res = null;
        try{
            StringBuilder output = new StringBuilder();
            fileReader = new FileReader(file.getAbsoluteFile());
            buffferedReader = new BufferedReader(fileReader);
            String line = "";

            while((line = buffferedReader.readLine()) != null) {
                output.append(line + "\n");
            }
            response = output.toString();
            buffferedReader.close();

            JSONObject messageDetails = new JSONObject(response);
            Boolean isUserExisting = messageDetails.has(name);
            if (!isUserExisting) {
                res = "Welcome !  " + name + "  Start Your First Game!";
            } else {
                res = "Welcome Back !  " + name + "  One more Game !";
            }
            fileWriter = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fileWriter);
            bw.write(messageDetails.toString());
            bw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {

        } catch (JSONException e) {

        }
        return res;
    }

    public static String getSettingJSON(String name) {
        String res = null;
        try{
            StringBuilder output = new StringBuilder();
            fileReader = new FileReader(file.getAbsoluteFile());
            buffferedReader = new BufferedReader(fileReader);
            String line = "";

            while((line = buffferedReader.readLine()) != null) {
                output.append(line + "\n");
            }
            response = output.toString();
            buffferedReader.close();

            JSONObject messageDetails = new JSONObject(response);
            res = messageDetails.toString();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {

        } catch (JSONException e) {

        }
        return res;
    }


    public static PriorityQueue<Map.Entry<String, Integer>> getScoreStats() {
        Map<String, Integer> map = new HashMap<>();
        PriorityQueue<Map.Entry<String, Integer>> minHeap =
                new PriorityQueue<>(10, new MyComparator());

        try{
            StringBuilder output = new StringBuilder();
            fileReader = new FileReader(file.getAbsoluteFile());
            buffferedReader = new BufferedReader(fileReader);
            String line = "";

            while((line = buffferedReader.readLine()) != null) {
                output.append(line + "\n");
            }
            response = output.toString();
            buffferedReader.close();

            JSONObject messageDetails = new JSONObject(response);
            Iterator<String> iter = messageDetails.keys();
            while (iter.hasNext()) {
                String key = iter.next();
                JSONArray userMessage = (JSONArray) messageDetails.get(key);
                JSONObject scoreObject = (JSONObject) userMessage.get(0);
                int max = (Integer) scoreObject.get("score");

//                Log.v(TAG, "userMessageKey : " + key + userMessage.length());

                map.put(key,max);
                Log.v(TAG, "key : " +  key + " value : " + max);
            }

            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                minHeap.offer(entry);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {

        } catch (JSONException e) {

        }

        return minHeap;
    }
}
