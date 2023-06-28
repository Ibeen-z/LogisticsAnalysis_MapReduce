<template>
  <div ref="mapEcharts" class="map-echart"></div>
</template>

<script>
import * as echarts from 'echarts'
import china from '../common/china'
import provance from '../common/provence'
export default {
  name: "echartsMap",
  data() {
    return {
      seriesData: [
      ],
      geoCoordMap: {
        '北京市': [116.4, 39.9],
        '天津市': [117.2, 39.12],
        '上海市': [121.47, 31.23],
        '重庆市': [106.55, 29.57],
        '河北省': [114.52, 38.05],
        '河南省': [113.62, 34.75],
        '云南省': [102.72, 25.05],
        '辽宁省': [123.43, 41.8],
        '黑龙江省': [126.53, 45.8],
        '湖南省': [112.93, 28.23],
        '安徽省': [117.25, 31.83],
        '山东省': [116.98, 36.67],
        '新疆省': [87.62, 43.82],
        '江苏省': [118.78, 32.07],
        '浙江省': [120.15, 30.28],
        '江西省': [115.85, 28.68],
        '湖北省': [114.30, 30.6],
        '广西壮族自治区': [108.37, 22.82],
        '甘肃省': [103.82, 36.07],
        '山西省': [112.55, 37.87],
        '内蒙古自治区': [111.73, 40.83],
        '陕西省': [108.93, 34.27],
        '吉林省': [125.32, 43.9],
        '福建省': [119.3, 26.08],
        '贵州省': [106.63, 26.65],
        '广东省': [113.27, 23.13],
        '青海省': [101.78, 36.62],
        '西藏自治区': [91.13, 29.65],
        '四川省': [104.07, 30.67],
        '宁夏回族自治区': [106.28, 38.47],
        '海南省': [110.32, 20.03],
        '台湾省': [121.5, 25.03],
        '香港特别行政区': [114.08, 22.20],
        '澳门特别行政区': [113.33, 23.13],
        '新疆维吾尔自治区': [87.617733, 43.792818],
        '西藏自治区': [91.132212, 29.660361],
      }
    }
  },
  mounted() {
     this.initData();
  },
  methods: {
    convertData(data, value) {
      var res = [];
      for (var i = 0; i < data.length; i++) {
        var geoCoord = this.geoCoordMap[data[i].name];
        if (geoCoord) {
          res.push({
            name: data[i].name,
            value: geoCoord.concat(data[i].value)
          });
        }
      }
      //去除小于300的小值
      res.forEach((item, index1) => {
        res.forEach((item2, index2) => {
          if (index1 < index2) {
            const s = this.getDistance(item.value[0], item.value[1], item2.value[0], item2.value[1])
            if (s < value) {
              if (item.value[3] > item2.value[3]) {
                res.splice(index1, 1)
              } else {
                res.splice(index2, 1)
              }
            }
          }
        })
      })
      return res;
    },
    //经纬度判断距离
    getDistance(e1, n1, e2, n2) {
      const R = 6371
      const { sin, cos, asin, PI, hypot } = Math

      /** 根据经纬度获取点的坐标 */
      let getPoint = (e, n) => {
        e *= PI / 180
        n *= PI / 180
        //这里 R* 被去掉, 相当于先求单位圆上两点的距, 最后会再将这个距离放大 R 倍
        return { x: cos(n) * cos(e), y: cos(n) * sin(e), z: sin(n) }
      }

      let a = getPoint(e1, n1)
      let b = getPoint(e2, n2)
      let c = hypot(a.x - b.x, a.y - b.y, a.z - b.z)
      let r = asin(c / 2) * 2 * R
      return r
    },
    /**初始化地图 */
    initMapEcharts() {
      echarts.registerMap('china', china)
      this.$nextTick(() => {
        this.map = echarts.init(this.$refs['mapEcharts'])
        const option = {
          title: {
            left: 'center',
            textStyle: {
              color: '#fff',
              fontSize: 32
            }
          },
          tooltip: {
            trigger: 'item'
          },
          // 图例
          visualMap: {
            min: 5,
            max: 250,
            show: false,
            text: ['High', 'Low'],
            realtime: false,
            calculable: false,
            inRange: {
              color: ['#e7f2fb', '#76a5af', '#dd7e6b']
            }
          },
          geo: {
            map: 'china',
            label: {
              emphasis: {
                show: false
              }
            },
            roam: true,
            // layoutCenter: ['110%', '110%'],
            // 如果宽高比大于 1 则宽度为 100，如果小于 1 则高度为 100，保证了不超过 100x100 的区域
            itemStyle: {
              normal: {
                borderColor: 'rgba(147, 235, 248, 1)',
                borderWidth: 1,
                areaColor: {
                  type: 'radial',
                  x: 0.5,
                  y: 0.5,
                  r: 0.8,
                  colorStops: [{
                    offset: 0,
                    color: 'rgba(175,238,238, 0)' // 0% 处的颜色
                  }, {
                    offset: 1,
                    color: 'rgba(47,79,79, .1)' // 100% 处的颜色
                  }],
                  globalCoord: false // 缺省为 false
                },
                shadowColor: 'rgba(128, 217, 248, 1)',
                shadowOffsetX: -2,
                shadowOffsetY: 2,
                shadowBlur: 10
              },
              emphasis: {
                areaColor: '#389BB7',
                borderWidth: 0
              }
            }
          },
          // 要显示的散点数据
          series: [
            {
              type: 'map',
              map: 'china',
              // 这是要显示的数据
              name: '订单数量',
              roam: true,
              data: this.seriesData,
            },
            {
              type: 'effectScatter',
              coordinateSystem: 'geo',
              data: this.pointData,
              symbolSize: function (val) {
                if (val[2] < 60) {
                  return 6
                }
                if (val[2] > 180) {
                  return 15
                }
                return val[2] / 12;
              },
              encode: {
                value: 2
              },
              showEffectOn: 'render',
              rippleEffect: {
                brushType: 'stroke'
              },
              label: {
                formatter: '{b}',
                position: 'right',
                show: false
              },
              itemStyle: {
                shadowBlur: 10,
                shadowColor: '#333'
              },
              emphasis: {
                scale: true
              },
              zlevel: 1
            }
          ]
        }
        option.series = [...option.series, ...this.LineSeries]
        this.map.setOption(option)
      })
    },
    /**初始化数据 */
    initData() {
      this.$http.get('/api/cityData').then(res => {
        let data2 = res.data.data
      let arr3 = []
        for (const k in provance.provinces) {
          provance.provinces[k].citys.map(i => {
            for (const key in data2) {
              let count = 0
              if (data2[key].name + '市' == i.cityName ) {
                count += data2[key].count
                let lastObj = {
                  name: provance.provinces[k].provinceName,
                  value: count
                }
                arr3.push(lastObj)
              }
            }
          })
        }
        this.seriesData = arr3
        this.chooseWareHouse();
        this.initLineData(this.seriesData)
        this.initMapEcharts();
      })

    },
    /**处理仓库选址数据 */
    chooseWareHouse() {
      let c = []
      let d = {}
      this.seriesData.forEach(e => {
        if (!d[e.name]) {
          c.push({
            name: e.name,
            value: e.value
          })
          d[e.name] = e
        }
      })
      c.sort((a, b) => {
        return b.value - a.value
      })

      this.seriesData = c
      this.pointData = this.convertData(
        this.seriesData
          .sort(function (a, b) {
            return b.value - a.value;
          })
      )
      const wareHouseData = this.convertData(
        this.seriesData
          .sort(function (a, b) {
            return b.value - a.value;
          }).slice(0, 6), 300
      )
      this.wareHouseData = wareHouseData
    },
    /**地图路径数据 */
    initLineData(arr2) {
      /**物流路线数据 */
      let arrObj = {},
        lineData = []

      this.lineData = lineData

      /**城市-经纬度 */
      let provanceAddress = []
      for (const key in china.features) {
        provanceAddress.push({ name: china.features[key].properties.name, value: china.features[key].properties.center })

      }

      //数据处理--省份-订单数-经纬度
      arr2.forEach(item => {
        if (arrObj.hasOwnProperty(item.name)) {
          arrObj[item.name] += item.value
        } else {
          arrObj[item.name] = item.value
          lineData.push({ name: item.name, value: item.value })
        }
      })
      const mergedArr = [];

      for (let i = 0; i < provanceAddress.length; i++) {
        for (let j = 0; j < arr2.length; j++) {
          if (provanceAddress[i].name === arr2[j].name) {
            mergedArr.push({ name: arr2[j].name, value: arr2[j].value, address: provanceAddress[i].value });
          }
        }
      }

      /**计算距离选仓 */
      let finalLineData = []
      mergedArr.forEach(item1 => {
        let minDist = Infinity
        let closestWarehouse;
        this.wareHouseData.forEach(item2 => {
          const s = this.getDistance(item1.address[0], item1.address[1], item2.value[0], item2.value[1])
          if (s - minDist < 0) {
            minDist = s
            closestWarehouse = [item2.name, [{ name: item1.name }, { name: item2.name, value: item2.value }]]
          }
        })
        finalLineData.push(closestWarehouse)
      })

      /**处理线条数据函数 */
      let initData = (data) => {
        var res = [];
        for (var i = 0; i < data.length; i++) {
          var dataItem = data[i];
          var fromCoord = this.geoCoordMap[dataItem[0].name];
          var toCoord = this.geoCoordMap[dataItem[1].name];
          if (fromCoord && toCoord) {
            res.push({
              fromName: dataItem[0].name,
              toName: dataItem[1].name,
              coords: [toCoord, fromCoord]
            });
          }
        }
        return res;
      };

      let series = []
      finalLineData.forEach((item) => {
        //边界处理-同城市
        if (item[0] !== item[1][0].name) {
          /**添加线条数据 */
          series.push(
            {
              name: item[0],
              type: 'lines',
              zlevel: 2,
              symbol: ['none', 'arrow'],
              symbolSize: 4,
              effect: {
                show: true,
                period: 6,
                trailLength: 0,
                symbol: 'arrow',
                symbolSize: 5
              },
              lineStyle: {
                normal: {
                  width: 1.4,
                  opacity: 1,
                  curveness: 0.2
                }
              },
              data: initData(item)
            }
          )
        }
      })
      this.LineSeries = series
    }
  },
  beforeDestroy() {
    echarts.dispose();
  }
}
</script>

<style></style>
