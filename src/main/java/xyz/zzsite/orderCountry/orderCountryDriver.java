package xyz.zzsite.orderCountry;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import xyz.zzsite.warehouse.logisticsMapper;
import xyz.zzsite.warehouse.logisticsReducer;


public class orderCountryDriver {
    public static void main(String[] args) throws Exception {

        Configuration conf = new Configuration();

        Job job = Job.getInstance(conf, "OrderCityCount");
        job.setJarByClass(xyz.zzsite.orderCountry.orderCountryDriver.class);

        Path inputPath = new Path("D:\\Users\\zZ\\Desktop\\myDemo\\output.csv");
        Path outputPath = new Path("output_国家订单数");

        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        TextInputFormat.addInputPath(job, inputPath);
        TextOutputFormat.setOutputPath(job, outputPath);

        job.setMapperClass(orderCountryMapper.class);
        job.setReducerClass(orderCountryReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
