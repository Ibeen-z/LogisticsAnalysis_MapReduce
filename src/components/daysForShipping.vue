<template>
     <div class="mychart" id="mychart7"></div>
</template>

<script>
import * as echarts from "echarts";
export default {
     name: "daysForShipping",
     data() {
          return {
               data: [],
               xData: [],
               yData: []
          }
     },
     mounted() {
          this.getData()
     },
     methods: {
          getData() {
               this.$http.get('/api/daysForShipping').then(res => {
                    res.data.data.forEach(item => {
                         this.xData.push(item.name)
                         this.yData.push(item.value)
                    });
                    this.data = res.data.data
                    this.initEcharts()
               })
          },
          initEcharts() {
               const  option = {
                    title: {
                         text: '发货风险', 
                         left: 'center',
                         textStyle: {
                              color: '#fff',
                              fontWeight: 400
                         }
                    },
                    tooltip: {
                         trigger: 'item',
                         textStyle: {
                              color: '#fff',
                              fontWeight: 400
                         }
                    },
                    legend: {
                         orient: 'vertical',
                         left: 'left',
                         textStyle: {
                              color: '#fff',
                              fontWeight: 400
                         }
                    },
                    series: [
                         {
                              name: '订单数',
                              type: 'pie',
                              radius: '70%',
                              data: this.data,
                              itemStyle: {
                                   normal: {
                                        color: function (params) {
                                             var colorList = ['#3d85c6', '#71caaf', '#76a5af'];
                                             return colorList[params.dataIndex]
                                        }
                                   }
                              },
                              emphasis: {
                                   itemStyle: {
                                        shadowBlur: 10,
                                        shadowOffsetX: 0,
                                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                                   }
                              }
                         }
                    ]
               };
               const myChart = echarts.init(document.getElementById("mychart7"));
               myChart.setOption(option);
          }
     }
}
</script>

<style scoped>
.mychart {
     height: 100%;
     width: 100%;
}
</style>