package xyz.zzsite.orderCountry;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import xyz.zzsite.Utils.DbUtils;

import java.io.IOException;



public class orderCountryReducer extends Reducer<Text, LongWritable, Text, LongWritable> {

    public void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {

        long count = 0;
        for (LongWritable value : values) {
            count += value.get();
        }
        DbUtils.initOrderCountry("orderCountry", key.toString(), count);
        context.write(key, new LongWritable(count));
    }
}