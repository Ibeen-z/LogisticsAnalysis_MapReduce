package xyz.zzsite.shoppingMode;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import xyz.zzsite.warehouse.logisticsDriver;
import xyz.zzsite.warehouse.logisticsMapper;
import xyz.zzsite.warehouse.logisticsReducer;

public class ShippingDriver {

    public static void main(String[] args) throws Exception {

//        if (args.length != 2) {
//            System.err.println("Usage: ShippingDriver <input path> <output path>");
//            System.exit(-1);
//        }
//
//        // create a Hadoop job and set the main class
//        Configuration conf = new Configuration();
//        Job job = Job.getInstance(conf, "Shipping Analysis");
//        job.setJarByClass(ShippingDriver.class);
//
//        // set the Mapper and Reducer classes
//        job.setMapperClass(ShippingMapper.class);
//        job.setReducerClass(ShippingReducer.class);
//
//        // set the types of the output key-value pairs
//        job.setOutputKeyClass(Text.class);
//        job.setOutputValueClass(LongWritable.class);
//
//        // set the input and output file paths
//        FileInputFormat.addInputPath(job, new Path(args[0]));
//        FileOutputFormat.setOutputPath(job, new Path(args[1]));
//
//        // wait for the job to complete and print the result
//        System.exit(job.waitForCompletion(true) ? 0 : 1);
//


        // Create configuration
        Configuration conf = new Configuration();

        // Create job
        Job job = Job.getInstance(conf, "shipping");
        job.setJarByClass(ShippingDriver.class);

        // Setup input and output paths
        Path inputPath = new Path("D:\\Users\\zZ\\Desktop\\myDemo\\output.csv");
//        Path outputPath = new Path("D:\\Users\\zZ\\Desktop\\myDemo\\output");
        Path outputPath = new Path("output_发货模式");

        // Setup input and output formats
        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        // Set input and output paths
        TextInputFormat.addInputPath(job, inputPath);
        TextOutputFormat.setOutputPath(job, outputPath);

        // Set mapper, reducer, and combiner classes
        job.setMapperClass(ShippingMapper.class);
        job.setReducerClass(ShippingReducer.class);

        // Set output key and value classes
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(DoubleWritable.class);

        // Submit job
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
