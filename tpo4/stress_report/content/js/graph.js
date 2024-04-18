/*
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
$(document).ready(function() {

    $(".click-title").mouseenter( function(    e){
        e.preventDefault();
        this.style.cursor="pointer";
    });
    $(".click-title").mousedown( function(event){
        event.preventDefault();
    });

    // Ugly code while this script is shared among several pages
    try{
        refreshHitsPerSecond(true);
    } catch(e){}
    try{
        refreshResponseTimeOverTime(true);
    } catch(e){}
    try{
        refreshResponseTimePercentiles();
    } catch(e){}
});


var responseTimePercentilesInfos = {
        data: {"result": {"minY": 6.0, "minX": 0.0, "maxY": 1088.0, "series": [{"data": [[0.0, 6.0], [0.1, 6.0], [0.2, 6.0], [0.3, 6.0], [0.4, 16.0], [0.5, 16.0], [0.6, 16.0], [0.7, 63.0], [0.8, 63.0], [0.9, 63.0], [1.0, 88.0], [1.1, 88.0], [1.2, 88.0], [1.3, 102.0], [1.4, 102.0], [1.5, 102.0], [1.6, 113.0], [1.7, 113.0], [1.8, 113.0], [1.9, 113.0], [2.0, 158.0], [2.1, 158.0], [2.2, 158.0], [2.3, 176.0], [2.4, 176.0], [2.5, 176.0], [2.6, 188.0], [2.7, 188.0], [2.8, 188.0], [2.9, 213.0], [3.0, 213.0], [3.1, 213.0], [3.2, 237.0], [3.3, 237.0], [3.4, 237.0], [3.5, 240.0], [3.6, 240.0], [3.7, 240.0], [3.8, 240.0], [3.9, 242.0], [4.0, 242.0], [4.1, 242.0], [4.2, 263.0], [4.3, 263.0], [4.4, 263.0], [4.5, 286.0], [4.6, 286.0], [4.7, 286.0], [4.8, 290.0], [4.9, 290.0], [5.0, 290.0], [5.1, 295.0], [5.2, 295.0], [5.3, 295.0], [5.4, 299.0], [5.5, 299.0], [5.6, 299.0], [5.7, 299.0], [5.8, 311.0], [5.9, 311.0], [6.0, 311.0], [6.1, 345.0], [6.2, 345.0], [6.3, 345.0], [6.4, 352.0], [6.5, 352.0], [6.6, 352.0], [6.7, 368.0], [6.8, 368.0], [6.9, 368.0], [7.0, 383.0], [7.1, 383.0], [7.2, 383.0], [7.3, 383.0], [7.4, 431.0], [7.5, 431.0], [7.6, 431.0], [7.7, 431.0], [7.8, 431.0], [7.9, 431.0], [8.0, 434.0], [8.1, 434.0], [8.2, 434.0], [8.3, 444.0], [8.4, 444.0], [8.5, 444.0], [8.6, 446.0], [8.7, 446.0], [8.8, 446.0], [8.9, 453.0], [9.0, 453.0], [9.1, 453.0], [9.2, 453.0], [9.3, 457.0], [9.4, 457.0], [9.5, 457.0], [9.6, 457.0], [9.7, 457.0], [9.8, 457.0], [9.9, 457.0], [10.0, 457.0], [10.1, 457.0], [10.2, 458.0], [10.3, 458.0], [10.4, 458.0], [10.5, 458.0], [10.6, 458.0], [10.7, 458.0], [10.8, 458.0], [10.9, 458.0], [11.0, 458.0], [11.1, 458.0], [11.2, 463.0], [11.3, 463.0], [11.4, 463.0], [11.5, 474.0], [11.6, 474.0], [11.7, 474.0], [11.8, 485.0], [11.9, 485.0], [12.0, 485.0], [12.1, 486.0], [12.2, 486.0], [12.3, 486.0], [12.4, 490.0], [12.5, 490.0], [12.6, 490.0], [12.7, 495.0], [12.8, 495.0], [12.9, 495.0], [13.0, 495.0], [13.1, 496.0], [13.2, 496.0], [13.3, 496.0], [13.4, 497.0], [13.5, 497.0], [13.6, 497.0], [13.7, 499.0], [13.8, 499.0], [13.9, 499.0], [14.0, 500.0], [14.1, 500.0], [14.2, 500.0], [14.3, 500.0], [14.4, 500.0], [14.5, 500.0], [14.6, 500.0], [14.7, 503.0], [14.8, 503.0], [14.9, 503.0], [15.0, 504.0], [15.1, 504.0], [15.2, 504.0], [15.3, 505.0], [15.4, 505.0], [15.5, 505.0], [15.6, 505.0], [15.7, 505.0], [15.8, 505.0], [15.9, 506.0], [16.0, 506.0], [16.1, 506.0], [16.2, 507.0], [16.3, 507.0], [16.4, 507.0], [16.5, 507.0], [16.6, 508.0], [16.7, 508.0], [16.8, 508.0], [16.9, 508.0], [17.0, 508.0], [17.1, 508.0], [17.2, 509.0], [17.3, 509.0], [17.4, 509.0], [17.5, 510.0], [17.6, 510.0], [17.7, 510.0], [17.8, 513.0], [17.9, 513.0], [18.0, 513.0], [18.1, 513.0], [18.2, 513.0], [18.3, 513.0], [18.4, 513.0], [18.5, 514.0], [18.6, 514.0], [18.7, 514.0], [18.8, 514.0], [18.9, 514.0], [19.0, 514.0], [19.1, 514.0], [19.2, 514.0], [19.3, 514.0], [19.4, 514.0], [19.5, 514.0], [19.6, 514.0], [19.7, 515.0], [19.8, 515.0], [19.9, 515.0], [20.0, 515.0], [20.1, 515.0], [20.2, 515.0], [20.3, 515.0], [20.4, 516.0], [20.5, 516.0], [20.6, 516.0], [20.7, 516.0], [20.8, 516.0], [20.9, 516.0], [21.0, 516.0], [21.1, 516.0], [21.2, 516.0], [21.3, 517.0], [21.4, 517.0], [21.5, 517.0], [21.6, 518.0], [21.7, 518.0], [21.8, 518.0], [21.9, 518.0], [22.0, 518.0], [22.1, 518.0], [22.2, 518.0], [22.3, 521.0], [22.4, 521.0], [22.5, 521.0], [22.6, 522.0], [22.7, 522.0], [22.8, 522.0], [22.9, 527.0], [23.0, 527.0], [23.1, 527.0], [23.2, 530.0], [23.3, 530.0], [23.4, 530.0], [23.5, 532.0], [23.6, 532.0], [23.7, 532.0], [23.8, 532.0], [23.9, 533.0], [24.0, 533.0], [24.1, 533.0], [24.2, 533.0], [24.3, 533.0], [24.4, 533.0], [24.5, 534.0], [24.6, 534.0], [24.7, 534.0], [24.8, 539.0], [24.9, 539.0], [25.0, 539.0], [25.1, 539.0], [25.2, 539.0], [25.3, 539.0], [25.4, 540.0], [25.5, 540.0], [25.6, 540.0], [25.7, 540.0], [25.8, 542.0], [25.9, 542.0], [26.0, 542.0], [26.1, 542.0], [26.2, 542.0], [26.3, 542.0], [26.4, 543.0], [26.5, 543.0], [26.6, 543.0], [26.7, 543.0], [26.8, 543.0], [26.9, 543.0], [27.0, 543.0], [27.1, 543.0], [27.2, 543.0], [27.3, 543.0], [27.4, 545.0], [27.5, 545.0], [27.6, 545.0], [27.7, 545.0], [27.8, 545.0], [27.9, 545.0], [28.0, 546.0], [28.1, 546.0], [28.2, 546.0], [28.3, 547.0], [28.4, 547.0], [28.5, 547.0], [28.6, 548.0], [28.7, 548.0], [28.8, 548.0], [28.9, 548.0], [29.0, 548.0], [29.1, 548.0], [29.2, 548.0], [29.3, 548.0], [29.4, 548.0], [29.5, 548.0], [29.6, 549.0], [29.7, 549.0], [29.8, 549.0], [29.9, 549.0], [30.0, 549.0], [30.1, 549.0], [30.2, 549.0], [30.3, 549.0], [30.4, 549.0], [30.5, 549.0], [30.6, 549.0], [30.7, 549.0], [30.8, 550.0], [30.9, 550.0], [31.0, 550.0], [31.1, 550.0], [31.2, 551.0], [31.3, 551.0], [31.4, 551.0], [31.5, 553.0], [31.6, 553.0], [31.7, 553.0], [31.8, 554.0], [31.9, 554.0], [32.0, 554.0], [32.1, 554.0], [32.2, 554.0], [32.3, 554.0], [32.4, 555.0], [32.5, 555.0], [32.6, 555.0], [32.7, 555.0], [32.8, 555.0], [32.9, 555.0], [33.0, 555.0], [33.1, 555.0], [33.2, 555.0], [33.3, 555.0], [33.4, 555.0], [33.5, 555.0], [33.6, 555.0], [33.7, 556.0], [33.8, 556.0], [33.9, 556.0], [34.0, 556.0], [34.1, 556.0], [34.2, 556.0], [34.3, 557.0], [34.4, 557.0], [34.5, 557.0], [34.6, 557.0], [34.7, 557.0], [34.8, 557.0], [34.9, 557.0], [35.0, 558.0], [35.1, 558.0], [35.2, 558.0], [35.3, 559.0], [35.4, 559.0], [35.5, 559.0], [35.6, 559.0], [35.7, 559.0], [35.8, 559.0], [35.9, 559.0], [36.0, 559.0], [36.1, 559.0], [36.2, 561.0], [36.3, 561.0], [36.4, 561.0], [36.5, 561.0], [36.6, 561.0], [36.7, 561.0], [36.8, 561.0], [36.9, 561.0], [37.0, 561.0], [37.1, 561.0], [37.2, 562.0], [37.3, 562.0], [37.4, 562.0], [37.5, 563.0], [37.6, 563.0], [37.7, 563.0], [37.8, 564.0], [37.9, 564.0], [38.0, 564.0], [38.1, 564.0], [38.2, 564.0], [38.3, 564.0], [38.4, 564.0], [38.5, 565.0], [38.6, 565.0], [38.7, 565.0], [38.8, 565.0], [38.9, 565.0], [39.0, 565.0], [39.1, 566.0], [39.2, 566.0], [39.3, 566.0], [39.4, 567.0], [39.5, 567.0], [39.6, 567.0], [39.7, 567.0], [39.8, 567.0], [39.9, 567.0], [40.0, 567.0], [40.1, 567.0], [40.2, 567.0], [40.3, 567.0], [40.4, 569.0], [40.5, 569.0], [40.6, 569.0], [40.7, 570.0], [40.8, 570.0], [40.9, 570.0], [41.0, 570.0], [41.1, 570.0], [41.2, 570.0], [41.3, 572.0], [41.4, 572.0], [41.5, 572.0], [41.6, 573.0], [41.7, 573.0], [41.8, 573.0], [41.9, 573.0], [42.0, 573.0], [42.1, 573.0], [42.2, 573.0], [42.3, 573.0], [42.4, 573.0], [42.5, 573.0], [42.6, 573.0], [42.7, 573.0], [42.8, 573.0], [42.9, 573.0], [43.0, 573.0], [43.1, 573.0], [43.2, 573.0], [43.3, 573.0], [43.4, 573.0], [43.5, 573.0], [43.6, 573.0], [43.7, 573.0], [43.8, 573.0], [43.9, 573.0], [44.0, 573.0], [44.1, 573.0], [44.2, 574.0], [44.3, 574.0], [44.4, 574.0], [44.5, 574.0], [44.6, 574.0], [44.7, 574.0], [44.8, 574.0], [44.9, 574.0], [45.0, 574.0], [45.1, 575.0], [45.2, 575.0], [45.3, 575.0], [45.4, 575.0], [45.5, 575.0], [45.6, 575.0], [45.7, 575.0], [45.8, 575.0], [45.9, 575.0], [46.0, 575.0], [46.1, 576.0], [46.2, 576.0], [46.3, 576.0], [46.4, 576.0], [46.5, 576.0], [46.6, 576.0], [46.7, 576.0], [46.8, 576.0], [46.9, 576.0], [47.0, 576.0], [47.1, 576.0], [47.2, 576.0], [47.3, 576.0], [47.4, 577.0], [47.5, 577.0], [47.6, 577.0], [47.7, 577.0], [47.8, 577.0], [47.9, 577.0], [48.0, 578.0], [48.1, 578.0], [48.2, 578.0], [48.3, 578.0], [48.4, 578.0], [48.5, 578.0], [48.6, 579.0], [48.7, 579.0], [48.8, 579.0], [48.9, 579.0], [49.0, 579.0], [49.1, 579.0], [49.2, 579.0], [49.3, 583.0], [49.4, 583.0], [49.5, 583.0], [49.6, 583.0], [49.7, 583.0], [49.8, 583.0], [49.9, 586.0], [50.0, 586.0], [50.1, 586.0], [50.2, 591.0], [50.3, 591.0], [50.4, 591.0], [50.5, 591.0], [50.6, 591.0], [50.7, 591.0], [50.8, 595.0], [50.9, 595.0], [51.0, 595.0], [51.1, 595.0], [51.2, 597.0], [51.3, 597.0], [51.4, 597.0], [51.5, 602.0], [51.6, 602.0], [51.7, 602.0], [51.8, 603.0], [51.9, 603.0], [52.0, 603.0], [52.1, 604.0], [52.2, 604.0], [52.3, 604.0], [52.4, 605.0], [52.5, 605.0], [52.6, 605.0], [52.7, 606.0], [52.8, 606.0], [52.9, 606.0], [53.0, 606.0], [53.1, 608.0], [53.2, 608.0], [53.3, 608.0], [53.4, 608.0], [53.5, 608.0], [53.6, 608.0], [53.7, 608.0], [53.8, 608.0], [53.9, 608.0], [54.0, 609.0], [54.1, 609.0], [54.2, 609.0], [54.3, 609.0], [54.4, 609.0], [54.5, 609.0], [54.6, 609.0], [54.7, 610.0], [54.8, 610.0], [54.9, 610.0], [55.0, 610.0], [55.1, 610.0], [55.2, 610.0], [55.3, 612.0], [55.4, 612.0], [55.5, 612.0], [55.6, 613.0], [55.7, 613.0], [55.8, 613.0], [55.9, 615.0], [56.0, 615.0], [56.1, 615.0], [56.2, 616.0], [56.3, 616.0], [56.4, 616.0], [56.5, 616.0], [56.6, 621.0], [56.7, 621.0], [56.8, 621.0], [56.9, 622.0], [57.0, 622.0], [57.1, 622.0], [57.2, 623.0], [57.3, 623.0], [57.4, 623.0], [57.5, 623.0], [57.6, 623.0], [57.7, 623.0], [57.8, 624.0], [57.9, 624.0], [58.0, 624.0], [58.1, 625.0], [58.2, 625.0], [58.3, 625.0], [58.4, 625.0], [58.5, 625.0], [58.6, 625.0], [58.7, 625.0], [58.8, 626.0], [58.9, 626.0], [59.0, 626.0], [59.1, 626.0], [59.2, 626.0], [59.3, 626.0], [59.4, 629.0], [59.5, 629.0], [59.6, 629.0], [59.7, 630.0], [59.8, 630.0], [59.9, 630.0], [60.0, 631.0], [60.1, 631.0], [60.2, 631.0], [60.3, 631.0], [60.4, 633.0], [60.5, 633.0], [60.6, 633.0], [60.7, 633.0], [60.8, 633.0], [60.9, 633.0], [61.0, 633.0], [61.1, 633.0], [61.2, 633.0], [61.3, 633.0], [61.4, 633.0], [61.5, 633.0], [61.6, 634.0], [61.7, 634.0], [61.8, 634.0], [61.9, 634.0], [62.0, 634.0], [62.1, 634.0], [62.2, 634.0], [62.3, 635.0], [62.4, 635.0], [62.5, 635.0], [62.6, 635.0], [62.7, 635.0], [62.8, 635.0], [62.9, 636.0], [63.0, 636.0], [63.1, 636.0], [63.2, 636.0], [63.3, 636.0], [63.4, 636.0], [63.5, 636.0], [63.6, 636.0], [63.7, 636.0], [63.8, 636.0], [63.9, 638.0], [64.0, 638.0], [64.1, 638.0], [64.2, 639.0], [64.3, 639.0], [64.4, 639.0], [64.5, 639.0], [64.6, 639.0], [64.7, 639.0], [64.8, 641.0], [64.9, 641.0], [65.0, 641.0], [65.1, 643.0], [65.2, 643.0], [65.3, 643.0], [65.4, 647.0], [65.5, 647.0], [65.6, 647.0], [65.7, 647.0], [65.8, 653.0], [65.9, 653.0], [66.0, 653.0], [66.1, 653.0], [66.2, 653.0], [66.3, 653.0], [66.4, 657.0], [66.5, 657.0], [66.6, 657.0], [66.7, 660.0], [66.8, 660.0], [66.9, 660.0], [67.0, 660.0], [67.1, 660.0], [67.2, 660.0], [67.3, 660.0], [67.4, 661.0], [67.5, 661.0], [67.6, 661.0], [67.7, 662.0], [67.8, 662.0], [67.9, 662.0], [68.0, 663.0], [68.1, 663.0], [68.2, 663.0], [68.3, 663.0], [68.4, 663.0], [68.5, 663.0], [68.6, 663.0], [68.7, 663.0], [68.8, 663.0], [68.9, 663.0], [69.0, 663.0], [69.1, 663.0], [69.2, 663.0], [69.3, 666.0], [69.4, 666.0], [69.5, 666.0], [69.6, 671.0], [69.7, 671.0], [69.8, 671.0], [69.9, 673.0], [70.0, 673.0], [70.1, 673.0], [70.2, 674.0], [70.3, 674.0], [70.4, 674.0], [70.5, 675.0], [70.6, 675.0], [70.7, 675.0], [70.8, 675.0], [70.9, 675.0], [71.0, 675.0], [71.1, 675.0], [71.2, 676.0], [71.3, 676.0], [71.4, 676.0], [71.5, 676.0], [71.6, 676.0], [71.7, 676.0], [71.8, 676.0], [71.9, 676.0], [72.0, 676.0], [72.1, 677.0], [72.2, 677.0], [72.3, 677.0], [72.4, 677.0], [72.5, 677.0], [72.6, 677.0], [72.7, 677.0], [72.8, 677.0], [72.9, 677.0], [73.0, 677.0], [73.1, 677.0], [73.2, 677.0], [73.3, 677.0], [73.4, 677.0], [73.5, 677.0], [73.6, 677.0], [73.7, 678.0], [73.8, 678.0], [73.9, 678.0], [74.0, 679.0], [74.1, 679.0], [74.2, 679.0], [74.3, 679.0], [74.4, 679.0], [74.5, 679.0], [74.6, 679.0], [74.7, 679.0], [74.8, 679.0], [74.9, 679.0], [75.0, 679.0], [75.1, 679.0], [75.2, 679.0], [75.3, 680.0], [75.4, 680.0], [75.5, 680.0], [75.6, 681.0], [75.7, 681.0], [75.8, 681.0], [75.9, 682.0], [76.0, 682.0], [76.1, 682.0], [76.2, 683.0], [76.3, 683.0], [76.4, 683.0], [76.5, 683.0], [76.6, 686.0], [76.7, 686.0], [76.8, 686.0], [76.9, 686.0], [77.0, 686.0], [77.1, 686.0], [77.2, 688.0], [77.3, 688.0], [77.4, 688.0], [77.5, 689.0], [77.6, 689.0], [77.7, 689.0], [77.8, 691.0], [77.9, 691.0], [78.0, 691.0], [78.1, 693.0], [78.2, 693.0], [78.3, 693.0], [78.4, 693.0], [78.5, 693.0], [78.6, 693.0], [78.7, 693.0], [78.8, 695.0], [78.9, 695.0], [79.0, 695.0], [79.1, 710.0], [79.2, 710.0], [79.3, 710.0], [79.4, 714.0], [79.5, 714.0], [79.6, 714.0], [79.7, 714.0], [79.8, 714.0], [79.9, 714.0], [80.0, 724.0], [80.1, 724.0], [80.2, 724.0], [80.3, 724.0], [80.4, 724.0], [80.5, 724.0], [80.6, 724.0], [80.7, 728.0], [80.8, 728.0], [80.9, 728.0], [81.0, 728.0], [81.1, 728.0], [81.2, 728.0], [81.3, 728.0], [81.4, 728.0], [81.5, 728.0], [81.6, 736.0], [81.7, 736.0], [81.8, 736.0], [81.9, 736.0], [82.0, 737.0], [82.1, 737.0], [82.2, 737.0], [82.3, 737.0], [82.4, 737.0], [82.5, 737.0], [82.6, 737.0], [82.7, 737.0], [82.8, 737.0], [82.9, 737.0], [83.0, 737.0], [83.1, 737.0], [83.2, 738.0], [83.3, 738.0], [83.4, 738.0], [83.5, 739.0], [83.6, 739.0], [83.7, 739.0], [83.8, 739.0], [83.9, 739.0], [84.0, 739.0], [84.1, 739.0], [84.2, 745.0], [84.3, 745.0], [84.4, 745.0], [84.5, 745.0], [84.6, 745.0], [84.7, 745.0], [84.8, 746.0], [84.9, 746.0], [85.0, 746.0], [85.1, 748.0], [85.2, 748.0], [85.3, 748.0], [85.4, 752.0], [85.5, 752.0], [85.6, 752.0], [85.7, 752.0], [85.8, 752.0], [85.9, 752.0], [86.0, 752.0], [86.1, 754.0], [86.2, 754.0], [86.3, 754.0], [86.4, 755.0], [86.5, 755.0], [86.6, 755.0], [86.7, 755.0], [86.8, 755.0], [86.9, 755.0], [87.0, 764.0], [87.1, 764.0], [87.2, 764.0], [87.3, 764.0], [87.4, 764.0], [87.5, 764.0], [87.6, 764.0], [87.7, 766.0], [87.8, 766.0], [87.9, 766.0], [88.0, 788.0], [88.1, 788.0], [88.2, 788.0], [88.3, 799.0], [88.4, 799.0], [88.5, 799.0], [88.6, 819.0], [88.7, 819.0], [88.8, 819.0], [88.9, 820.0], [89.0, 820.0], [89.1, 820.0], [89.2, 820.0], [89.3, 823.0], [89.4, 823.0], [89.5, 823.0], [89.6, 824.0], [89.7, 824.0], [89.8, 824.0], [89.9, 825.0], [90.0, 825.0], [90.1, 825.0], [90.2, 857.0], [90.3, 857.0], [90.4, 857.0], [90.5, 859.0], [90.6, 859.0], [90.7, 859.0], [90.8, 865.0], [90.9, 865.0], [91.0, 865.0], [91.1, 865.0], [91.2, 874.0], [91.3, 874.0], [91.4, 874.0], [91.5, 875.0], [91.6, 875.0], [91.7, 875.0], [91.8, 875.0], [91.9, 875.0], [92.0, 875.0], [92.1, 875.0], [92.2, 875.0], [92.3, 875.0], [92.4, 877.0], [92.5, 877.0], [92.6, 877.0], [92.7, 877.0], [92.8, 877.0], [92.9, 877.0], [93.0, 877.0], [93.1, 879.0], [93.2, 879.0], [93.3, 879.0], [93.4, 879.0], [93.5, 879.0], [93.6, 879.0], [93.7, 880.0], [93.8, 880.0], [93.9, 880.0], [94.0, 881.0], [94.1, 881.0], [94.2, 881.0], [94.3, 884.0], [94.4, 884.0], [94.5, 884.0], [94.6, 884.0], [94.7, 896.0], [94.8, 896.0], [94.9, 896.0], [95.0, 901.0], [95.1, 901.0], [95.2, 901.0], [95.3, 908.0], [95.4, 908.0], [95.5, 908.0], [95.6, 925.0], [95.7, 925.0], [95.8, 925.0], [95.9, 935.0], [96.0, 935.0], [96.1, 935.0], [96.2, 935.0], [96.3, 935.0], [96.4, 935.0], [96.5, 935.0], [96.6, 936.0], [96.7, 936.0], [96.8, 936.0], [96.9, 938.0], [97.0, 938.0], [97.1, 938.0], [97.2, 938.0], [97.3, 938.0], [97.4, 938.0], [97.5, 940.0], [97.6, 940.0], [97.7, 940.0], [97.8, 944.0], [97.9, 944.0], [98.0, 944.0], [98.1, 963.0], [98.2, 963.0], [98.3, 963.0], [98.4, 963.0], [98.5, 963.0], [98.6, 963.0], [98.7, 963.0], [98.8, 979.0], [98.9, 979.0], [99.0, 979.0], [99.1, 996.0], [99.2, 996.0], [99.3, 996.0], [99.4, 1016.0], [99.5, 1016.0], [99.6, 1016.0], [99.7, 1088.0], [99.8, 1088.0], [99.9, 1088.0]], "isOverall": false, "label": "conf #3 req", "isController": false}], "supportsControllersDiscrimination": true, "maxX": 100.0, "title": "Response Time Percentiles"}},
        getOptions: function() {
            return {
                series: {
                    points: { show: false }
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: '#legendResponseTimePercentiles'
                },
                xaxis: {
                    tickDecimals: 1,
                    axisLabel: "Percentiles",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Percentile value in ms",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s : %x.2 percentile was %y ms"
                },
                selection: { mode: "xy" },
            };
        },
        createGraph: function() {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesResponseTimePercentiles"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotResponseTimesPercentiles"), dataset, options);
            // setup overview
            $.plot($("#overviewResponseTimesPercentiles"), dataset, prepareOverviewOptions(options));
        }
};

/**
 * @param elementId Id of element where we display message
 */
function setEmptyGraph(elementId) {
    $(function() {
        $(elementId).text("No graph series with filter="+seriesFilter);
    });
}

// Response times percentiles
function refreshResponseTimePercentiles() {
    var infos = responseTimePercentilesInfos;
    prepareSeries(infos.data);
    if(infos.data.result.series.length == 0) {
        setEmptyGraph("#bodyResponseTimePercentiles");
        return;
    }
    if (isGraph($("#flotResponseTimesPercentiles"))){
        infos.createGraph();
    } else {
        var choiceContainer = $("#choicesResponseTimePercentiles");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotResponseTimesPercentiles", "#overviewResponseTimesPercentiles");
        $('#bodyResponseTimePercentiles .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
}

var responseTimeDistributionInfos = {
        data: {"result": {"minY": 2.0, "minX": 0.0, "maxY": 118.0, "series": [{"data": [[0.0, 4.0], [600.0, 87.0], [300.0, 5.0], [700.0, 30.0], [400.0, 21.0], [800.0, 20.0], [200.0, 9.0], [100.0, 5.0], [900.0, 14.0], [500.0, 118.0], [1000.0, 2.0]], "isOverall": false, "label": "conf #3 req", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 100, "maxX": 1000.0, "title": "Response Time Distribution"}},
        getOptions: function() {
            var granularity = this.data.result.granularity;
            return {
                legend: {
                    noColumns: 2,
                    show: true,
                    container: '#legendResponseTimeDistribution'
                },
                xaxis:{
                    axisLabel: "Response times in ms",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Number of responses",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                bars : {
                    show: true,
                    barWidth: this.data.result.granularity
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: function(label, xval, yval, flotItem){
                        return yval + " responses for " + label + " were between " + xval + " and " + (xval + granularity) + " ms";
                    }
                }
            };
        },
        createGraph: function() {
            var data = this.data;
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotResponseTimeDistribution"), prepareData(data.result.series, $("#choicesResponseTimeDistribution")), options);
        }

};

// Response time distribution
function refreshResponseTimeDistribution() {
    var infos = responseTimeDistributionInfos;
    prepareSeries(infos.data);
    if(infos.data.result.series.length == 0) {
        setEmptyGraph("#bodyResponseTimeDistribution");
        return;
    }
    if (isGraph($("#flotResponseTimeDistribution"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesResponseTimeDistribution");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        $('#footerResponseTimeDistribution .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};


var syntheticResponseTimeDistributionInfos = {
        data: {"result": {"minY": 19.0, "minX": 0.0, "ticks": [[0, "Requests having \nresponse time <= 500ms"], [1, "Requests having \nresponse time > 500ms and <= 1 500ms"], [2, "Requests having \nresponse time > 1 500ms"], [3, "Requests in error"]], "maxY": 243.0, "series": [{"data": [[0.0, 19.0]], "color": "#9ACD32", "isOverall": false, "label": "Requests having \nresponse time <= 500ms", "isController": false}, {"data": [[1.0, 243.0]], "color": "yellow", "isOverall": false, "label": "Requests having \nresponse time > 500ms and <= 1 500ms", "isController": false}, {"data": [], "color": "orange", "isOverall": false, "label": "Requests having \nresponse time > 1 500ms", "isController": false}, {"data": [[3.0, 53.0]], "color": "#FF6347", "isOverall": false, "label": "Requests in error", "isController": false}], "supportsControllersDiscrimination": false, "maxX": 3.0, "title": "Synthetic Response Times Distribution"}},
        getOptions: function() {
            return {
                legend: {
                    noColumns: 2,
                    show: true,
                    container: '#legendSyntheticResponseTimeDistribution'
                },
                xaxis:{
                    axisLabel: "Response times ranges",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                    tickLength:0,
                    min:-0.5,
                    max:3.5
                },
                yaxis: {
                    axisLabel: "Number of responses",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                bars : {
                    show: true,
                    align: "center",
                    barWidth: 0.25,
                    fill:.75
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: function(label, xval, yval, flotItem){
                        return yval + " " + label;
                    }
                }
            };
        },
        createGraph: function() {
            var data = this.data;
            var options = this.getOptions();
            prepareOptions(options, data);
            options.xaxis.ticks = data.result.ticks;
            $.plot($("#flotSyntheticResponseTimeDistribution"), prepareData(data.result.series, $("#choicesSyntheticResponseTimeDistribution")), options);
        }

};

// Response time distribution
function refreshSyntheticResponseTimeDistribution() {
    var infos = syntheticResponseTimeDistributionInfos;
    prepareSeries(infos.data, true);
    if (isGraph($("#flotSyntheticResponseTimeDistribution"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesSyntheticResponseTimeDistribution");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        $('#footerSyntheticResponseTimeDistribution .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

var activeThreadsOverTimeInfos = {
        data: {"result": {"minY": 12.666666666666666, "minX": 1.71346416E12, "maxY": 92.57051282051282, "series": [{"data": [[1.71346422E12, 92.57051282051282], [1.71346416E12, 12.666666666666666]], "isOverall": false, "label": "conf #3 users", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.71346422E12, "title": "Active Threads Over Time"}},
        getOptions: function() {
            return {
                series: {
                    stack: true,
                    lines: {
                        show: true,
                        fill: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Number of active threads",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20
                },
                legend: {
                    noColumns: 6,
                    show: true,
                    container: '#legendActiveThreadsOverTime'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                selection: {
                    mode: 'xy'
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s : At %x there were %y active threads"
                }
            };
        },
        createGraph: function() {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesActiveThreadsOverTime"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotActiveThreadsOverTime"), dataset, options);
            // setup overview
            $.plot($("#overviewActiveThreadsOverTime"), dataset, prepareOverviewOptions(options));
        }
};

// Active Threads Over Time
function refreshActiveThreadsOverTime(fixTimestamps) {
    var infos = activeThreadsOverTimeInfos;
    prepareSeries(infos.data);
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 10800000);
    }
    if(isGraph($("#flotActiveThreadsOverTime"))) {
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesActiveThreadsOverTime");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotActiveThreadsOverTime", "#overviewActiveThreadsOverTime");
        $('#footerActiveThreadsOverTime .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

var timeVsThreadsInfos = {
        data: {"result": {"minY": 188.0, "minX": 7.0, "maxY": 1016.0, "series": [{"data": [[7.0, 188.0], [11.0, 621.0], [13.0, 662.0], [14.0, 677.0], [15.0, 623.3333333333334], [17.0, 653.0], [18.0, 663.0], [19.0, 682.0], [20.0, 661.0], [21.0, 428.0], [22.0, 794.5], [23.0, 679.0], [24.0, 813.0], [25.0, 693.0], [26.0, 686.0], [27.0, 451.0], [28.0, 1016.0], [30.0, 740.0], [31.0, 531.25], [32.0, 611.0], [33.0, 745.0], [34.0, 604.0], [35.0, 522.0], [36.0, 398.5], [37.0, 300.3333333333333], [38.0, 533.0], [39.0, 505.0], [40.0, 944.0], [42.0, 590.5], [43.0, 576.0], [44.0, 541.0], [45.0, 708.0], [47.0, 556.0], [46.0, 213.0], [48.0, 559.0], [49.0, 567.0], [50.0, 565.0], [51.0, 650.5], [52.0, 561.0], [53.0, 549.0], [54.0, 668.0], [55.0, 591.5], [56.0, 564.0], [57.0, 570.0], [58.0, 412.0], [59.0, 537.5], [60.0, 535.0], [61.0, 529.5], [63.0, 549.75], [64.0, 500.0], [65.0, 605.5], [66.0, 532.0], [68.0, 583.0], [69.0, 521.6666666666666], [70.0, 554.0], [71.0, 590.5], [72.0, 461.0], [73.0, 542.0], [74.0, 427.0], [75.0, 605.5], [77.0, 578.6666666666666], [78.0, 596.5], [79.0, 567.0], [80.0, 737.25], [81.0, 574.0], [82.0, 571.2], [83.0, 527.8], [84.0, 343.0], [85.0, 502.0], [86.0, 487.0], [87.0, 453.0], [88.0, 555.0], [89.0, 518.0], [90.0, 503.0], [91.0, 543.0], [92.0, 413.0], [93.0, 456.5], [94.0, 512.0], [95.0, 524.3333333333334], [96.0, 534.0], [97.0, 537.0], [98.0, 540.0], [99.0, 610.0], [100.0, 558.3333333333334], [101.0, 548.5], [102.0, 527.5], [103.0, 545.5], [104.0, 531.5], [105.0, 537.0], [106.0, 577.5], [107.0, 573.0], [108.0, 558.0], [109.0, 455.75], [110.0, 704.6666666666666], [111.0, 661.0], [112.0, 576.75], [113.0, 561.6666666666666], [114.0, 552.5], [115.0, 456.75], [116.0, 536.25], [117.0, 556.0], [118.0, 607.25], [119.0, 423.0], [120.0, 631.0], [121.0, 572.0], [122.0, 604.0], [123.0, 726.3333333333334], [124.0, 574.4], [125.0, 620.5], [126.0, 707.75], [127.0, 648.0], [128.0, 510.75], [129.0, 707.5], [130.0, 675.0], [132.0, 696.3333333333334], [133.0, 608.75], [134.0, 702.0], [135.0, 693.75], [136.0, 701.5], [137.0, 777.0], [138.0, 724.0], [139.0, 748.0], [140.0, 776.6666666666666], [141.0, 779.0], [142.0, 794.6666666666666], [143.0, 823.6666666666666], [144.0, 773.5], [147.0, 888.3333333333333], [149.0, 885.1666666666666], [150.0, 334.0], [148.0, 679.0], [152.0, 932.6666666666666]], "isOverall": false, "label": "conf #3 req", "isController": false}, {"data": [[91.81269841269837, 606.0920634920636]], "isOverall": false, "label": "conf #3 req-Aggregated", "isController": false}], "supportsControllersDiscrimination": true, "maxX": 152.0, "title": "Time VS Threads"}},
        getOptions: function() {
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    axisLabel: "Number of active threads",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Average response times in ms",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20
                },
                legend: { noColumns: 2,show: true, container: '#legendTimeVsThreads' },
                selection: {
                    mode: 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s: At %x.2 active threads, Average response time was %y.2 ms"
                }
            };
        },
        createGraph: function() {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesTimeVsThreads"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotTimesVsThreads"), dataset, options);
            // setup overview
            $.plot($("#overviewTimesVsThreads"), dataset, prepareOverviewOptions(options));
        }
};

// Time vs threads
function refreshTimeVsThreads(){
    var infos = timeVsThreadsInfos;
    prepareSeries(infos.data);
    if(infos.data.result.series.length == 0) {
        setEmptyGraph("#bodyTimeVsThreads");
        return;
    }
    if(isGraph($("#flotTimesVsThreads"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesTimeVsThreads");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotTimesVsThreads", "#overviewTimesVsThreads");
        $('#footerTimeVsThreads .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

var bytesThroughputOverTimeInfos = {
        data : {"result": {"minY": 8.0, "minX": 1.71346416E12, "maxY": 3254.95, "series": [{"data": [[1.71346422E12, 3254.95], [1.71346416E12, 11.55]], "isOverall": false, "label": "Bytes received per second", "isController": false}, {"data": [[1.71346422E12, 690.6666666666666], [1.71346416E12, 8.0]], "isOverall": false, "label": "Bytes sent per second", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.71346422E12, "title": "Bytes Throughput Over Time"}},
        getOptions : function(){
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity) ,
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Bytes / sec",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: '#legendBytesThroughputOverTime'
                },
                selection: {
                    mode: "xy"
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s at %x was %y"
                }
            };
        },
        createGraph : function() {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesBytesThroughputOverTime"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotBytesThroughputOverTime"), dataset, options);
            // setup overview
            $.plot($("#overviewBytesThroughputOverTime"), dataset, prepareOverviewOptions(options));
        }
};

// Bytes throughput Over Time
function refreshBytesThroughputOverTime(fixTimestamps) {
    var infos = bytesThroughputOverTimeInfos;
    prepareSeries(infos.data);
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 10800000);
    }
    if(isGraph($("#flotBytesThroughputOverTime"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesBytesThroughputOverTime");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotBytesThroughputOverTime", "#overviewBytesThroughputOverTime");
        $('#footerBytesThroughputOverTime .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
}

var responseTimesOverTimeInfos = {
        data: {"result": {"minY": 605.6378205128209, "minX": 1.71346416E12, "maxY": 653.3333333333334, "series": [{"data": [[1.71346422E12, 605.6378205128209], [1.71346416E12, 653.3333333333334]], "isOverall": false, "label": "conf #3 req", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.71346422E12, "title": "Response Time Over Time"}},
        getOptions: function(){
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Average response time in ms",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: '#legendResponseTimesOverTime'
                },
                selection: {
                    mode: 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s : at %x Average response time was %y ms"
                }
            };
        },
        createGraph: function() {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesResponseTimesOverTime"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotResponseTimesOverTime"), dataset, options);
            // setup overview
            $.plot($("#overviewResponseTimesOverTime"), dataset, prepareOverviewOptions(options));
        }
};

// Response Times Over Time
function refreshResponseTimeOverTime(fixTimestamps) {
    var infos = responseTimesOverTimeInfos;
    prepareSeries(infos.data);
    if(infos.data.result.series.length == 0) {
        setEmptyGraph("#bodyResponseTimeOverTime");
        return;
    }
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 10800000);
    }
    if(isGraph($("#flotResponseTimesOverTime"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesResponseTimesOverTime");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotResponseTimesOverTime", "#overviewResponseTimesOverTime");
        $('#footerResponseTimesOverTime .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

var latenciesOverTimeInfos = {
        data: {"result": {"minY": 519.3846153846149, "minX": 1.71346416E12, "maxY": 653.0, "series": [{"data": [[1.71346422E12, 519.3846153846149], [1.71346416E12, 653.0]], "isOverall": false, "label": "conf #3 req", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.71346422E12, "title": "Latencies Over Time"}},
        getOptions: function() {
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Average response latencies in ms",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: '#legendLatenciesOverTime'
                },
                selection: {
                    mode: 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s : at %x Average latency was %y ms"
                }
            };
        },
        createGraph: function () {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesLatenciesOverTime"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotLatenciesOverTime"), dataset, options);
            // setup overview
            $.plot($("#overviewLatenciesOverTime"), dataset, prepareOverviewOptions(options));
        }
};

// Latencies Over Time
function refreshLatenciesOverTime(fixTimestamps) {
    var infos = latenciesOverTimeInfos;
    prepareSeries(infos.data);
    if(infos.data.result.series.length == 0) {
        setEmptyGraph("#bodyLatenciesOverTime");
        return;
    }
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 10800000);
    }
    if(isGraph($("#flotLatenciesOverTime"))) {
        infos.createGraph();
    }else {
        var choiceContainer = $("#choicesLatenciesOverTime");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotLatenciesOverTime", "#overviewLatenciesOverTime");
        $('#footerLatenciesOverTime .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

var connectTimeOverTimeInfos = {
        data: {"result": {"minY": 0.49358974358974345, "minX": 1.71346416E12, "maxY": 0.6666666666666666, "series": [{"data": [[1.71346422E12, 0.49358974358974345], [1.71346416E12, 0.6666666666666666]], "isOverall": false, "label": "conf #3 req", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.71346422E12, "title": "Connect Time Over Time"}},
        getOptions: function() {
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getConnectTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Average Connect Time in ms",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: '#legendConnectTimeOverTime'
                },
                selection: {
                    mode: 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s : at %x Average connect time was %y ms"
                }
            };
        },
        createGraph: function () {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesConnectTimeOverTime"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotConnectTimeOverTime"), dataset, options);
            // setup overview
            $.plot($("#overviewConnectTimeOverTime"), dataset, prepareOverviewOptions(options));
        }
};

// Connect Time Over Time
function refreshConnectTimeOverTime(fixTimestamps) {
    var infos = connectTimeOverTimeInfos;
    prepareSeries(infos.data);
    if(infos.data.result.series.length == 0) {
        setEmptyGraph("#bodyConnectTimeOverTime");
        return;
    }
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 10800000);
    }
    if(isGraph($("#flotConnectTimeOverTime"))) {
        infos.createGraph();
    }else {
        var choiceContainer = $("#choicesConnectTimeOverTime");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotConnectTimeOverTime", "#overviewConnectTimeOverTime");
        $('#footerConnectTimeOverTime .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

var responseTimePercentilesOverTimeInfos = {
        data: {"result": {"minY": 444.0, "minX": 1.71346416E12, "maxY": 996.0, "series": [{"data": [[1.71346422E12, 996.0], [1.71346416E12, 677.0]], "isOverall": false, "label": "Max", "isController": false}, {"data": [[1.71346422E12, 799.0], [1.71346416E12, 677.0]], "isOverall": false, "label": "90th percentile", "isController": false}, {"data": [[1.71346422E12, 955.5999999999991], [1.71346416E12, 677.0]], "isOverall": false, "label": "99th percentile", "isController": false}, {"data": [[1.71346422E12, 879.0], [1.71346416E12, 677.0]], "isOverall": false, "label": "95th percentile", "isController": false}, {"data": [[1.71346422E12, 444.0], [1.71346416E12, 621.0]], "isOverall": false, "label": "Min", "isController": false}, {"data": [[1.71346422E12, 595.0], [1.71346416E12, 662.0]], "isOverall": false, "label": "Median", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.71346422E12, "title": "Response Time Percentiles Over Time (successful requests only)"}},
        getOptions: function() {
            return {
                series: {
                    lines: {
                        show: true,
                        fill: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Response Time in ms",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: '#legendResponseTimePercentilesOverTime'
                },
                selection: {
                    mode: 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s : at %x Response time was %y ms"
                }
            };
        },
        createGraph: function () {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesResponseTimePercentilesOverTime"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotResponseTimePercentilesOverTime"), dataset, options);
            // setup overview
            $.plot($("#overviewResponseTimePercentilesOverTime"), dataset, prepareOverviewOptions(options));
        }
};

// Response Time Percentiles Over Time
function refreshResponseTimePercentilesOverTime(fixTimestamps) {
    var infos = responseTimePercentilesOverTimeInfos;
    prepareSeries(infos.data);
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 10800000);
    }
    if(isGraph($("#flotResponseTimePercentilesOverTime"))) {
        infos.createGraph();
    }else {
        var choiceContainer = $("#choicesResponseTimePercentilesOverTime");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotResponseTimePercentilesOverTime", "#overviewResponseTimePercentilesOverTime");
        $('#footerResponseTimePercentilesOverTime .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};


var responseTimeVsRequestInfos = {
    data: {"result": {"minY": 495.0, "minX": 3.0, "maxY": 903.0, "series": [{"data": [[32.0, 574.0], [65.0, 903.0], [17.0, 679.0], [35.0, 513.0], [18.0, 536.0], [37.0, 554.0], [41.0, 752.0], [22.0, 559.0], [45.0, 631.0], [3.0, 662.0]], "isOverall": false, "label": "Successes", "isController": false}, {"data": [[65.0, 495.0]], "isOverall": false, "label": "Failures", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 1000, "maxX": 65.0, "title": "Response Time Vs Request"}},
    getOptions: function() {
        return {
            series: {
                lines: {
                    show: false
                },
                points: {
                    show: true
                }
            },
            xaxis: {
                axisLabel: "Global number of requests per second",
                axisLabelUseCanvas: true,
                axisLabelFontSizePixels: 12,
                axisLabelFontFamily: 'Verdana, Arial',
                axisLabelPadding: 20,
            },
            yaxis: {
                axisLabel: "Median Response Time in ms",
                axisLabelUseCanvas: true,
                axisLabelFontSizePixels: 12,
                axisLabelFontFamily: 'Verdana, Arial',
                axisLabelPadding: 20,
            },
            legend: {
                noColumns: 2,
                show: true,
                container: '#legendResponseTimeVsRequest'
            },
            selection: {
                mode: 'xy'
            },
            grid: {
                hoverable: true // IMPORTANT! this is needed for tooltip to work
            },
            tooltip: true,
            tooltipOpts: {
                content: "%s : Median response time at %x req/s was %y ms"
            },
            colors: ["#9ACD32", "#FF6347"]
        };
    },
    createGraph: function () {
        var data = this.data;
        var dataset = prepareData(data.result.series, $("#choicesResponseTimeVsRequest"));
        var options = this.getOptions();
        prepareOptions(options, data);
        $.plot($("#flotResponseTimeVsRequest"), dataset, options);
        // setup overview
        $.plot($("#overviewResponseTimeVsRequest"), dataset, prepareOverviewOptions(options));

    }
};

// Response Time vs Request
function refreshResponseTimeVsRequest() {
    var infos = responseTimeVsRequestInfos;
    prepareSeries(infos.data);
    if (isGraph($("#flotResponseTimeVsRequest"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesResponseTimeVsRequest");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotResponseTimeVsRequest", "#overviewResponseTimeVsRequest");
        $('#footerResponseRimeVsRequest .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};


var latenciesVsRequestInfos = {
    data: {"result": {"minY": 0.0, "minX": 3.0, "maxY": 903.0, "series": [{"data": [[32.0, 573.5], [65.0, 903.0], [17.0, 679.0], [35.0, 513.0], [18.0, 536.0], [37.0, 554.0], [41.0, 752.0], [22.0, 559.0], [45.0, 631.0], [3.0, 662.0]], "isOverall": false, "label": "Successes", "isController": false}, {"data": [[65.0, 0.0]], "isOverall": false, "label": "Failures", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 1000, "maxX": 65.0, "title": "Latencies Vs Request"}},
    getOptions: function() {
        return{
            series: {
                lines: {
                    show: false
                },
                points: {
                    show: true
                }
            },
            xaxis: {
                axisLabel: "Global number of requests per second",
                axisLabelUseCanvas: true,
                axisLabelFontSizePixels: 12,
                axisLabelFontFamily: 'Verdana, Arial',
                axisLabelPadding: 20,
            },
            yaxis: {
                axisLabel: "Median Latency in ms",
                axisLabelUseCanvas: true,
                axisLabelFontSizePixels: 12,
                axisLabelFontFamily: 'Verdana, Arial',
                axisLabelPadding: 20,
            },
            legend: { noColumns: 2,show: true, container: '#legendLatencyVsRequest' },
            selection: {
                mode: 'xy'
            },
            grid: {
                hoverable: true // IMPORTANT! this is needed for tooltip to work
            },
            tooltip: true,
            tooltipOpts: {
                content: "%s : Median Latency time at %x req/s was %y ms"
            },
            colors: ["#9ACD32", "#FF6347"]
        };
    },
    createGraph: function () {
        var data = this.data;
        var dataset = prepareData(data.result.series, $("#choicesLatencyVsRequest"));
        var options = this.getOptions();
        prepareOptions(options, data);
        $.plot($("#flotLatenciesVsRequest"), dataset, options);
        // setup overview
        $.plot($("#overviewLatenciesVsRequest"), dataset, prepareOverviewOptions(options));
    }
};

// Latencies vs Request
function refreshLatenciesVsRequest() {
        var infos = latenciesVsRequestInfos;
        prepareSeries(infos.data);
        if(isGraph($("#flotLatenciesVsRequest"))){
            infos.createGraph();
        }else{
            var choiceContainer = $("#choicesLatencyVsRequest");
            createLegend(choiceContainer, infos);
            infos.createGraph();
            setGraphZoomable("#flotLatenciesVsRequest", "#overviewLatenciesVsRequest");
            $('#footerLatenciesVsRequest .legendColorBox > div').each(function(i){
                $(this).clone().prependTo(choiceContainer.find("li").eq(i));
            });
        }
};

var hitsPerSecondInfos = {
        data: {"result": {"minY": 0.23333333333333334, "minX": 1.71346416E12, "maxY": 5.016666666666667, "series": [{"data": [[1.71346422E12, 5.016666666666667], [1.71346416E12, 0.23333333333333334]], "isOverall": false, "label": "hitsPerSecond", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.71346422E12, "title": "Hits Per Second"}},
        getOptions: function() {
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Number of hits / sec",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: "#legendHitsPerSecond"
                },
                selection: {
                    mode : 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s at %x was %y.2 hits/sec"
                }
            };
        },
        createGraph: function createGraph() {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesHitsPerSecond"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotHitsPerSecond"), dataset, options);
            // setup overview
            $.plot($("#overviewHitsPerSecond"), dataset, prepareOverviewOptions(options));
        }
};

// Hits per second
function refreshHitsPerSecond(fixTimestamps) {
    var infos = hitsPerSecondInfos;
    prepareSeries(infos.data);
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 10800000);
    }
    if (isGraph($("#flotHitsPerSecond"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesHitsPerSecond");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotHitsPerSecond", "#overviewHitsPerSecond");
        $('#footerHitsPerSecond .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
}

var codesPerSecondInfos = {
        data: {"result": {"minY": 0.05, "minX": 1.71346416E12, "maxY": 4.316666666666666, "series": [{"data": [[1.71346422E12, 4.316666666666666], [1.71346416E12, 0.05]], "isOverall": false, "label": "200", "isController": false}, {"data": [[1.71346422E12, 0.8833333333333333]], "isOverall": false, "label": "Non HTTP response code: java.net.SocketException", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.71346422E12, "title": "Codes Per Second"}},
        getOptions: function(){
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Number of responses / sec",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: "#legendCodesPerSecond"
                },
                selection: {
                    mode: 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "Number of Response Codes %s at %x was %y.2 responses / sec"
                }
            };
        },
    createGraph: function() {
        var data = this.data;
        var dataset = prepareData(data.result.series, $("#choicesCodesPerSecond"));
        var options = this.getOptions();
        prepareOptions(options, data);
        $.plot($("#flotCodesPerSecond"), dataset, options);
        // setup overview
        $.plot($("#overviewCodesPerSecond"), dataset, prepareOverviewOptions(options));
    }
};

// Codes per second
function refreshCodesPerSecond(fixTimestamps) {
    var infos = codesPerSecondInfos;
    prepareSeries(infos.data);
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 10800000);
    }
    if(isGraph($("#flotCodesPerSecond"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesCodesPerSecond");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotCodesPerSecond", "#overviewCodesPerSecond");
        $('#footerCodesPerSecond .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

var transactionsPerSecondInfos = {
        data: {"result": {"minY": 0.05, "minX": 1.71346416E12, "maxY": 4.316666666666666, "series": [{"data": [[1.71346422E12, 0.8833333333333333]], "isOverall": false, "label": "conf #3 req-failure", "isController": false}, {"data": [[1.71346422E12, 4.316666666666666], [1.71346416E12, 0.05]], "isOverall": false, "label": "conf #3 req-success", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.71346422E12, "title": "Transactions Per Second"}},
        getOptions: function(){
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Number of transactions / sec",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: "#legendTransactionsPerSecond"
                },
                selection: {
                    mode: 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s at %x was %y transactions / sec"
                }
            };
        },
    createGraph: function () {
        var data = this.data;
        var dataset = prepareData(data.result.series, $("#choicesTransactionsPerSecond"));
        var options = this.getOptions();
        prepareOptions(options, data);
        $.plot($("#flotTransactionsPerSecond"), dataset, options);
        // setup overview
        $.plot($("#overviewTransactionsPerSecond"), dataset, prepareOverviewOptions(options));
    }
};

// Transactions per second
function refreshTransactionsPerSecond(fixTimestamps) {
    var infos = transactionsPerSecondInfos;
    prepareSeries(infos.data);
    if(infos.data.result.series.length == 0) {
        setEmptyGraph("#bodyTransactionsPerSecond");
        return;
    }
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 10800000);
    }
    if(isGraph($("#flotTransactionsPerSecond"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesTransactionsPerSecond");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotTransactionsPerSecond", "#overviewTransactionsPerSecond");
        $('#footerTransactionsPerSecond .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

var totalTPSInfos = {
        data: {"result": {"minY": 0.05, "minX": 1.71346416E12, "maxY": 4.316666666666666, "series": [{"data": [[1.71346422E12, 4.316666666666666], [1.71346416E12, 0.05]], "isOverall": false, "label": "Transaction-success", "isController": false}, {"data": [[1.71346422E12, 0.8833333333333333]], "isOverall": false, "label": "Transaction-failure", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.71346422E12, "title": "Total Transactions Per Second"}},
        getOptions: function(){
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Number of transactions / sec",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: "#legendTotalTPS"
                },
                selection: {
                    mode: 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s at %x was %y transactions / sec"
                },
                colors: ["#9ACD32", "#FF6347"]
            };
        },
    createGraph: function () {
        var data = this.data;
        var dataset = prepareData(data.result.series, $("#choicesTotalTPS"));
        var options = this.getOptions();
        prepareOptions(options, data);
        $.plot($("#flotTotalTPS"), dataset, options);
        // setup overview
        $.plot($("#overviewTotalTPS"), dataset, prepareOverviewOptions(options));
    }
};

// Total Transactions per second
function refreshTotalTPS(fixTimestamps) {
    var infos = totalTPSInfos;
    // We want to ignore seriesFilter
    prepareSeries(infos.data, false, true);
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 10800000);
    }
    if(isGraph($("#flotTotalTPS"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesTotalTPS");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotTotalTPS", "#overviewTotalTPS");
        $('#footerTotalTPS .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

// Collapse the graph matching the specified DOM element depending the collapsed
// status
function collapse(elem, collapsed){
    if(collapsed){
        $(elem).parent().find(".fa-chevron-up").removeClass("fa-chevron-up").addClass("fa-chevron-down");
    } else {
        $(elem).parent().find(".fa-chevron-down").removeClass("fa-chevron-down").addClass("fa-chevron-up");
        if (elem.id == "bodyBytesThroughputOverTime") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshBytesThroughputOverTime(true);
            }
            document.location.href="#bytesThroughputOverTime";
        } else if (elem.id == "bodyLatenciesOverTime") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshLatenciesOverTime(true);
            }
            document.location.href="#latenciesOverTime";
        } else if (elem.id == "bodyCustomGraph") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshCustomGraph(true);
            }
            document.location.href="#responseCustomGraph";
        } else if (elem.id == "bodyConnectTimeOverTime") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshConnectTimeOverTime(true);
            }
            document.location.href="#connectTimeOverTime";
        } else if (elem.id == "bodyResponseTimePercentilesOverTime") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshResponseTimePercentilesOverTime(true);
            }
            document.location.href="#responseTimePercentilesOverTime";
        } else if (elem.id == "bodyResponseTimeDistribution") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshResponseTimeDistribution();
            }
            document.location.href="#responseTimeDistribution" ;
        } else if (elem.id == "bodySyntheticResponseTimeDistribution") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshSyntheticResponseTimeDistribution();
            }
            document.location.href="#syntheticResponseTimeDistribution" ;
        } else if (elem.id == "bodyActiveThreadsOverTime") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshActiveThreadsOverTime(true);
            }
            document.location.href="#activeThreadsOverTime";
        } else if (elem.id == "bodyTimeVsThreads") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshTimeVsThreads();
            }
            document.location.href="#timeVsThreads" ;
        } else if (elem.id == "bodyCodesPerSecond") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshCodesPerSecond(true);
            }
            document.location.href="#codesPerSecond";
        } else if (elem.id == "bodyTransactionsPerSecond") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshTransactionsPerSecond(true);
            }
            document.location.href="#transactionsPerSecond";
        } else if (elem.id == "bodyTotalTPS") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshTotalTPS(true);
            }
            document.location.href="#totalTPS";
        } else if (elem.id == "bodyResponseTimeVsRequest") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshResponseTimeVsRequest();
            }
            document.location.href="#responseTimeVsRequest";
        } else if (elem.id == "bodyLatenciesVsRequest") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshLatenciesVsRequest();
            }
            document.location.href="#latencyVsRequest";
        }
    }
}

/*
 * Activates or deactivates all series of the specified graph (represented by id parameter)
 * depending on checked argument.
 */
function toggleAll(id, checked){
    var placeholder = document.getElementById(id);

    var cases = $(placeholder).find(':checkbox');
    cases.prop('checked', checked);
    $(cases).parent().children().children().toggleClass("legend-disabled", !checked);

    var choiceContainer;
    if ( id == "choicesBytesThroughputOverTime"){
        choiceContainer = $("#choicesBytesThroughputOverTime");
        refreshBytesThroughputOverTime(false);
    } else if(id == "choicesResponseTimesOverTime"){
        choiceContainer = $("#choicesResponseTimesOverTime");
        refreshResponseTimeOverTime(false);
    }else if(id == "choicesResponseCustomGraph"){
        choiceContainer = $("#choicesResponseCustomGraph");
        refreshCustomGraph(false);
    } else if ( id == "choicesLatenciesOverTime"){
        choiceContainer = $("#choicesLatenciesOverTime");
        refreshLatenciesOverTime(false);
    } else if ( id == "choicesConnectTimeOverTime"){
        choiceContainer = $("#choicesConnectTimeOverTime");
        refreshConnectTimeOverTime(false);
    } else if ( id == "choicesResponseTimePercentilesOverTime"){
        choiceContainer = $("#choicesResponseTimePercentilesOverTime");
        refreshResponseTimePercentilesOverTime(false);
    } else if ( id == "choicesResponseTimePercentiles"){
        choiceContainer = $("#choicesResponseTimePercentiles");
        refreshResponseTimePercentiles();
    } else if(id == "choicesActiveThreadsOverTime"){
        choiceContainer = $("#choicesActiveThreadsOverTime");
        refreshActiveThreadsOverTime(false);
    } else if ( id == "choicesTimeVsThreads"){
        choiceContainer = $("#choicesTimeVsThreads");
        refreshTimeVsThreads();
    } else if ( id == "choicesSyntheticResponseTimeDistribution"){
        choiceContainer = $("#choicesSyntheticResponseTimeDistribution");
        refreshSyntheticResponseTimeDistribution();
    } else if ( id == "choicesResponseTimeDistribution"){
        choiceContainer = $("#choicesResponseTimeDistribution");
        refreshResponseTimeDistribution();
    } else if ( id == "choicesHitsPerSecond"){
        choiceContainer = $("#choicesHitsPerSecond");
        refreshHitsPerSecond(false);
    } else if(id == "choicesCodesPerSecond"){
        choiceContainer = $("#choicesCodesPerSecond");
        refreshCodesPerSecond(false);
    } else if ( id == "choicesTransactionsPerSecond"){
        choiceContainer = $("#choicesTransactionsPerSecond");
        refreshTransactionsPerSecond(false);
    } else if ( id == "choicesTotalTPS"){
        choiceContainer = $("#choicesTotalTPS");
        refreshTotalTPS(false);
    } else if ( id == "choicesResponseTimeVsRequest"){
        choiceContainer = $("#choicesResponseTimeVsRequest");
        refreshResponseTimeVsRequest();
    } else if ( id == "choicesLatencyVsRequest"){
        choiceContainer = $("#choicesLatencyVsRequest");
        refreshLatenciesVsRequest();
    }
    var color = checked ? "black" : "#818181";
    if(choiceContainer != null) {
        choiceContainer.find("label").each(function(){
            this.style.color = color;
        });
    }
}

