package xyz.zzsite.shoppingMode;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import xyz.zzsite.Utils.DbUtils;

public class ShippingReducer extends Reducer<Text, DoubleWritable, Text, DoubleWritable> {

    public void reduce(Text key, Iterable<DoubleWritable> values, Context context) throws IOException, InterruptedException {
        double sum = 0.0;
        int count = 0;
        for (DoubleWritable val : values) {
            sum += val.get();
            count++;
        }
        double average = sum / count;
        DbUtils.initShoppingMode("shoppingMode", key.toString(), average);
        context.write(key, new DoubleWritable(average));
    }
}
