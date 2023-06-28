package xyz.zzsite.orderCountry;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.io.IOException;

public class orderCountryMapper extends Mapper<LongWritable, Text, Text, LongWritable> {

    private final static LongWritable ONE = new LongWritable(1);

    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String[] fields = value.toString().split(",");

        String orderCity =  fields[25];
        String orderCountry = fields[26];

        context.write(new Text(orderCountry), ONE);

    }
}