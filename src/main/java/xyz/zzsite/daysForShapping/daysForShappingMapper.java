package xyz.zzsite.daysForShapping;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class daysForShappingMapper extends Mapper<LongWritable,Text,Text,Text>{
    private final Text outputValue = new Text();
    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context) throws IOException, InterruptedException {
        String[] fields = value.toString().split(",");

        if (key.get() == 0 && value.toString().contains("Order Id")) {
            System.out.println(fields[29]);
            return;
        }


        String orderID = fields[29];
        String daysForShippingReal = fields[1];
        String daysForShippingScheduled = fields[2];

        // 将orderID作为key，将daysForShippingReal和daysForShippingScheduled组合成一个字符串作为value
        outputValue.set(daysForShippingReal + "," + daysForShippingScheduled);
        context.write(new Text(orderID), outputValue);
    }
}