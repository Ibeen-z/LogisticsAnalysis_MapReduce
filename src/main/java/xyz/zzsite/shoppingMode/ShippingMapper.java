package xyz.zzsite.shoppingMode;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class ShippingMapper extends Mapper<LongWritable, Text, Text, DoubleWritable> {
    private Text shoppingMode = new Text();

    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] fields = value.toString().split(",");
        DoubleWritable daysForShippingReal = new DoubleWritable();
        //去除首行
        if (key.get() == 0 && value.toString().contains("Days for shipping (real)")) {
            System.out.println(fields[52]);
            return;
        }
        shoppingMode.set(fields[52]);
        daysForShippingReal.set( Double.parseDouble(fields[1]));
        context.write(shoppingMode, daysForShippingReal);
    }
}
