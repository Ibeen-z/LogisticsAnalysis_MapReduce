package xyz.zzsite.warehouse;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class logisticsMapper extends Mapper<LongWritable, Text, Text, LongWritable> {

    private final static LongWritable ONE = new LongWritable(1);

    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String[] fields = value.toString().split(",");

        String orderCity =  fields[25];
        String orderCountry = fields[26];

        if (orderCountry.equals("China")) {
            context.write(new Text(orderCity), ONE);
        }

    }
}