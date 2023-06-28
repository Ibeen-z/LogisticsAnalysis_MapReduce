package xyz.zzsite.categoryProfit;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import xyz.zzsite.shoppingMode.ShippingMapper;
import xyz.zzsite.shoppingMode.ShippingReducer;


public class OrderProfitDriver {

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();

        Job job = Job.getInstance(conf, "OrderProfit");
        job.setJarByClass(OrderProfitDriver.class);

        Path inputPath = new Path("D:\\Users\\zZ\\Desktop\\myDemo\\output.csv");
        Path outputPath = new Path("output_类别利润");
//        Path outputPath = new Path("output_类别利润");
        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        TextInputFormat.addInputPath(job, inputPath);
        TextOutputFormat.setOutputPath(job, outputPath);

        job.setMapperClass(OrderProfitMapper.class);
        job.setReducerClass(OrderProfitReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(DoubleWritable.class);

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
