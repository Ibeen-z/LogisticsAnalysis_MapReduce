package xyz.zzsite.LatLong;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class LatLongMapper extends Mapper<LongWritable, Text, Text, LongWritable> {

    private final static LongWritable ONE = new LongWritable(1);

    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String[] fields = value.toString().split(",");

        String orderCountry =  fields[26];
        String latLong = fields[25] + ',' + fields[22] + ','+ fields[23];

        if (orderCountry.equals("China")) {
            context.write(new Text(latLong), ONE);
        }
    }
}