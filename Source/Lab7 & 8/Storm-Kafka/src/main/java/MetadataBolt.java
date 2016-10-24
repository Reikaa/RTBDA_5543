import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.io.*;
import java.util.Map;

/**
 * Created by harsha on 10/19/16.
 */
public class MetadataBolt extends BaseBasicBolt {

    private OutputCollector collector;
//    private static Writer output;

//    @Override
//    public void prepare(Map stormConf, TopologyContext context,
//                        OutputCollector collector) {
//        this.collector = collector;
//
//    }
//
//    @Override
//    public void execute(Tuple input) {
//        String sentence = input.getString(0);
//        String[] words = sentence.split(" ");
//        for(String word: words){
//            word = word.trim();
//            if(!word.isEmpty()){
//                word = word.toLowerCase();
//                collector.emit(new Values(word));
//            }
//        }
//        collector.ack(input);
//    }

    @Override
    public void execute(Tuple tuple, BasicOutputCollector basicOutputCollector) {
        try {
            String s = tuple.getString(0);
            PrintWriter out = null;
            try {
                out = new PrintWriter(new BufferedWriter(new FileWriter("/home/harsha/Desktop/messages.txt", true)));
                out.println(s);
            }catch (IOException e) {
                System.err.println(e);
            }finally{
                if(out != null){
                    out.close();
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cleanup() {
        // TODO Auto-generated method stub

    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("word"));

    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        // TODO Auto-generated method stub
        return null;
    }
}
