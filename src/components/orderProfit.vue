<template>
     <div class="echart" id="mychart3"></div>
</template>
   
<script>
import * as echarts from "echarts";
import OrderProfit from '../common/OrderProfit'

export default {
     data() {
          return {
               xData: [], //横坐标
               yData: [], //数据
          };
     },
     mounted() {
          this.initData();
          this.initEcharts();
     },
     methods: {
          initData() {
               for (const key in OrderProfit) {
                    this.xData.push(key)
                    this.yData.push(Number(OrderProfit[key]).toFixed(2))
               }
          },
          initEcharts() {
               const option = {
                    title: {
                         text: '订单类型-利润',
                         textStyle: {
                              color: '#fff',
                              fontWeight: 400
                         }
                    },
                    tooltip: {
                         trigger: 'axis'
                    },
                    xAxis: {
                         name: '\n\n\n\n\n\n订单类型',
                         show: false,
                         nameTextStyle: {
                              color: '#fff',
                              align: 'right'
                         },
                         axisLabel: {
                              textStyle: {
                                   color: '#fff', //坐标轴字体颜色
                                   fontSize: 8
                              },
                         },
                         data: this.xData
                    },
                    yAxis: {
                         name: '利润',
                         nameTextStyle: {
                              color: '#fff'
                         },
                         axisLabel: {
                              textStyle: {
                                   color: '#fff', //坐标轴字体颜色
                              },
                         },
                    },
                    series: [
                         {
                              type: "line",
                              smooth: true,
                              data: this.yData
                         },

                    ]
               };
               const myChart = echarts.init(document.getElementById("mychart3"));
               myChart.setOption(option);
          }
     }
};
</script>
<style scoped>
.echart {
     height: 100%;
     padding: 10px;
}
</style>
   