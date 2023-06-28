package xyz.zzsite.warehouse;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import xyz.zzsite.Utils.DbUtils;

public class logisticsReducer extends Reducer<Text, LongWritable, Text, LongWritable> {

    public void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {

        long count = 0;
        for (LongWritable value : values) {
            count += value.get();
        }
        DbUtils.insertData("wareHouse", key.toString(), count);
        context.write(key, new LongWritable(count));
    }
}