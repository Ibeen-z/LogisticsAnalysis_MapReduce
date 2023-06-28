<template>
     <div class="echart" id="mychart"></div>
</template>
   
<script>
import * as echarts from "echarts";
export default {
     data() {
          return {
               xData: [], //横坐标
               yData: [], //数据
          };
     },
     mounted() {
          this.getShoppingMode()
     },
     methods: {
          getShoppingMode() {
               this.$http.get('/api/shoppingMode').then(res => {
                    if (res.data.status !== 0) return
                    let x = [], y = []
                    res.data.data.forEach(item => {
                         x.push(item.name)
                         y.push(item.value.toFixed(2))
                    });
                    this.xData = x
                    console.log('this.xData: ', this.xData);
                    this.yData = y
                    this.initEcharts();

                    console.log('this.xData: ', this.yData);

               })
          },
          initEcharts() {
               const option = {
                    title: {
                         text: '发货模式',
                         left: 'left',
                         textStyle: {
                              color: '#fff',
                              fontWeight: 400
                         }
                    },
                    xAxis: {
                         type: 'category',
                         data: this.xData,
                         axisLabel: { interval: 0, color: '#fff', fontWeight: 200, fontSize: 10 },
                    },
                    yAxis: {
                         type: 'value',
                         axisLabel: { interval: 0, color: '#fff', fontWeight: 200, fontSize: 10 },
                    },
                    tooltip: {
                         trigger: 'item',
                         textStyle: {
                              color: '#fff',
                              fontWeight: 400
                         }
                    },
                    series:
                         [{
                              name:'平均天数',
                              data: this.yData,
                              type: 'bar',
                              itemStyle: {
                                   normal: {
                                        color: function (params) {
                                             var colorList = ['#c23531', '#2f4554', '#61a0a8', '#d48265'];
                                             return colorList[params.dataIndex]
                                        }
                                   }
                              }
                         }]

               };
               const myChart = echarts.init(document.getElementById("mychart"));
               myChart.setOption(option);
          }
     }
};
</script>
<style scoped>
.echart {
     height: 100%;
}
</style>
   