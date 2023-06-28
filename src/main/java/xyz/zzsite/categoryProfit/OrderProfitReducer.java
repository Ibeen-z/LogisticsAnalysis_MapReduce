package xyz.zzsite.categoryProfit;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import xyz.zzsite.Utils.DbUtils;

import java.io.IOException;

public class OrderProfitReducer extends Reducer<Text, DoubleWritable, Text, DoubleWritable> {

    private DoubleWritable totalProfit = new DoubleWritable();

    public void reduce(Text key, Iterable<DoubleWritable> values, Context context) throws IOException, InterruptedException {
        double sum = 0;
        for (DoubleWritable val : values) {
            sum += val.get(); // 计算同一类别的订单利润总和
        }
        totalProfit.set(sum);
        DbUtils.initOrderProfit("orderProfit", key.toString(), sum);//存储数据库
        context.write(key, totalProfit); // 输出订单类别和总利润
    }
}

