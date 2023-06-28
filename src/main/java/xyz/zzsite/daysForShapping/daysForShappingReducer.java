package xyz.zzsite.daysForShapping;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import xyz.zzsite.Utils.DbUtils;

import java.io.IOException;

class daysForShappingReducer extends Reducer<Text, Text, Text, LongWritable> {
    int early = 0;
    int onTime = 0;
    int late = 0;
    @Override
    protected void reduce(Text key, Iterable<Text> values, Reducer<Text, Text, Text, LongWritable>.Context context) throws IOException, InterruptedException {
        double daysForShippingReal = 0.0;
        double daysForShippingScheduled = 0.0;
        for (Text value : values) {
            String[] fields = value.toString().split(",");
             daysForShippingReal =Double.parseDouble( fields[0]);
             daysForShippingScheduled = Double.parseDouble( fields[1]);
            if (daysForShippingReal < daysForShippingScheduled) {
                early++;
            }else if(daysForShippingReal == daysForShippingScheduled){
                onTime++;
            }else if(daysForShippingReal > daysForShippingScheduled) {
                late++;
            }
        }

    }
    public void cleanup(Context context) throws IOException, InterruptedException {
        DbUtils.initDaysForShapping("daysForShapping", new String("late"), late);
        DbUtils.initDaysForShapping("daysForShapping", new String("onTime"), onTime);
        DbUtils.initDaysForShapping("daysForShapping", new String("early"), early);
        context.write(new Text("early"), new LongWritable(early));
        context.write(new Text("onTime"), new LongWritable(onTime));
        context.write(new Text("late"), new LongWritable(late));
    }
}