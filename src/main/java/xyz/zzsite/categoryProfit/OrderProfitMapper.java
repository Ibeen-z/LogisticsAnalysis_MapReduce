package xyz.zzsite.categoryProfit;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.text.DecimalFormat;

public class OrderProfitMapper extends Mapper<LongWritable, Text, Text, DoubleWritable> {

    private Text category = new Text();
    private DoubleWritable profit = new DoubleWritable();

    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] fields = value.toString().split(",");
        //去除首行
        if (key.get() == 0 && value.toString().contains("Days for shipping (real)")) {
            System.out.println(fields[52]);
            return;
        }
        String category = fields[8]; // 获取订单类别
        double orderProfit = Double.parseDouble(fields[39]); // 获取订单利润
        profit.set(orderProfit);
        context.write(new Text(category), profit); // 输出订单类别和利润
    }
}

