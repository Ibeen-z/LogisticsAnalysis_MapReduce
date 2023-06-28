package xyz.zzsite.LatLong;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
//import org.codehaus.jettison.json.JSONObject;

public class LatLongReducer extends Reducer<Text, LongWritable, Text, LongWritable> {

    public void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {

        // Sum up the count of orders for each order city
        long count = 0;
        for (LongWritable value : values) {
            count += value.get();
        }
//        JSONObject json = new JSONObject();
//        json.put("city", key.toString());
//        json.put("count", count);
        // Output order city and its order count
        context.write(key, new LongWritable(count));
    }
}