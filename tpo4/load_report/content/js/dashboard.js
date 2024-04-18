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
var showControllersOnly = false;
var seriesFilter = "";
var filtersOnlySampleSeries = true;

/*
 * Add header in statistics table to group metrics by category
 * format
 *
 */
function summaryTableHeader(header) {
    var newRow = header.insertRow(-1);
    newRow.className = "tablesorter-no-sort";
    var cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 1;
    cell.innerHTML = "Requests";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 3;
    cell.innerHTML = "Executions";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 7;
    cell.innerHTML = "Response Times (ms)";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 1;
    cell.innerHTML = "Throughput";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 2;
    cell.innerHTML = "Network (KB/sec)";
    newRow.appendChild(cell);
}

/*
 * Populates the table identified by id parameter with the specified data and
 * format
 *
 */
function createTable(table, info, formatter, defaultSorts, seriesIndex, headerCreator) {
    var tableRef = table[0];

    // Create header and populate it with data.titles array
    var header = tableRef.createTHead();

    // Call callback is available
    if(headerCreator) {
        headerCreator(header);
    }

    var newRow = header.insertRow(-1);
    for (var index = 0; index < info.titles.length; index++) {
        var cell = document.createElement('th');
        cell.innerHTML = info.titles[index];
        newRow.appendChild(cell);
    }

    var tBody;

    // Create overall body if defined
    if(info.overall){
        tBody = document.createElement('tbody');
        tBody.className = "tablesorter-no-sort";
        tableRef.appendChild(tBody);
        var newRow = tBody.insertRow(-1);
        var data = info.overall.data;
        for(var index=0;index < data.length; index++){
            var cell = newRow.insertCell(-1);
            cell.innerHTML = formatter ? formatter(index, data[index]): data[index];
        }
    }

    // Create regular body
    tBody = document.createElement('tbody');
    tableRef.appendChild(tBody);

    var regexp;
    if(seriesFilter) {
        regexp = new RegExp(seriesFilter, 'i');
    }
    // Populate body with data.items array
    for(var index=0; index < info.items.length; index++){
        var item = info.items[index];
        if((!regexp || filtersOnlySampleSeries && !info.supportsControllersDiscrimination || regexp.test(item.data[seriesIndex]))
                &&
                (!showControllersOnly || !info.supportsControllersDiscrimination || item.isController)){
            if(item.data.length > 0) {
                var newRow = tBody.insertRow(-1);
                for(var col=0; col < item.data.length; col++){
                    var cell = newRow.insertCell(-1);
                    cell.innerHTML = formatter ? formatter(col, item.data[col]) : item.data[col];
                }
            }
        }
    }

    // Add support of columns sort
    table.tablesorter({sortList : defaultSorts});
}

$(document).ready(function() {

    // Customize table sorter default options
    $.extend( $.tablesorter.defaults, {
        theme: 'blue',
        cssInfoBlock: "tablesorter-no-sort",
        widthFixed: true,
        widgets: ['zebra']
    });

    var data = {"OkPercent": 40.93567251461988, "KoPercent": 59.06432748538012};
    var dataset = [
        {
            "label" : "FAIL",
            "data" : data.KoPercent,
            "color" : "#FF6347"
        },
        {
            "label" : "PASS",
            "data" : data.OkPercent,
            "color" : "#9ACD32"
        }];
    $.plot($("#flot-requests-summary"), dataset, {
        series : {
            pie : {
                show : true,
                radius : 1,
                label : {
                    show : true,
                    radius : 3 / 4,
                    formatter : function(label, series) {
                        return '<div style="font-size:8pt;text-align:center;padding:2px;color:white;">'
                            + label
                            + '<br/>'
                            + Math.round10(series.percent, -2)
                            + '%</div>';
                    },
                    background : {
                        opacity : 0.5,
                        color : '#000'
                    }
                }
            }
        },
        legend : {
            show : true
        }
    });

    // Creates APDEX table
    createTable($("#apdexTable"), {"supportsControllersDiscrimination": true, "overall": {"data": [0.3713450292397661, 500, 1500, "Total"], "isController": false}, "titles": ["Apdex", "T (Toleration threshold)", "F (Frustration threshold)", "Label"], "items": [{"data": [1.0, 500, 1500, "conf #3 req"], "isController": false}, {"data": [0.11403508771929824, 500, 1500, "conf #2 req"], "isController": false}, {"data": [0.0, 500, 1500, "conf #1 req"], "isController": false}]}, function(index, item){
        switch(index){
            case 0:
                item = item.toFixed(3);
                break;
            case 1:
            case 2:
                item = formatDuration(item);
                break;
        }
        return item;
    }, [[0, 0]], 3);

    // Create statistics table
    createTable($("#statisticsTable"), {"supportsControllersDiscrimination": true, "overall": {"data": ["Total", 171, 101, 59.06432748538012, 788.3859649122807, 337, 1297, 814.0, 1176.8, 1186.2, 1273.24, 3.5105727776637243, 0.9317741480188872, 0.5389037158694313], "isController": false}, "titles": ["Label", "#Samples", "FAIL", "Error %", "Average", "Min", "Max", "Median", "90th pct", "95th pct", "99th pct", "Transactions/s", "Received", "Sent"], "items": [{"data": ["conf #3 req", 57, 0, 0.0, 392.0, 337, 488, 392.0, 425.6, 447.29999999999984, 488.0, 1.1781727986771393, 0.2657792153265812, 0.184089499793303], "isController": false}, {"data": ["conf #2 req", 57, 44, 77.19298245614036, 806.0175438596491, 716, 915, 814.0, 844.4, 867.3, 915.0, 1.1702630012113249, 0.31061051286262753, 0.17964563615086127], "isController": false}, {"data": ["conf #1 req", 57, 57, 100.0, 1167.1403508771928, 712, 1297, 1169.0, 1240.2, 1261.3, 1297.0, 1.1703110563597166, 0.357240818447798, 0.1764449235191459], "isController": false}]}, function(index, item){
        switch(index){
            // Errors pct
            case 3:
                item = item.toFixed(2) + '%';
                break;
            // Mean
            case 4:
            // Mean
            case 7:
            // Median
            case 8:
            // Percentile 1
            case 9:
            // Percentile 2
            case 10:
            // Percentile 3
            case 11:
            // Throughput
            case 12:
            // Kbytes/s
            case 13:
            // Sent Kbytes/s
                item = item.toFixed(2);
                break;
        }
        return item;
    }, [[0, 0]], 0, summaryTableHeader);

    // Create error table
    createTable($("#errorsTable"), {"supportsControllersDiscrimination": false, "titles": ["Type of error", "Number of errors", "% in errors", "% in all samples"], "items": [{"data": ["The operation lasted too long: It took 829 milliseconds, but should not have lasted longer than 780 milliseconds.", 1, 0.9900990099009901, 0.5847953216374269], "isController": false}, {"data": ["The operation lasted too long: It took 1&nbsp;167 milliseconds, but should not have lasted longer than 780 milliseconds.", 1, 0.9900990099009901, 0.5847953216374269], "isController": false}, {"data": ["The operation lasted too long: It took 782 milliseconds, but should not have lasted longer than 780 milliseconds.", 2, 1.9801980198019802, 1.1695906432748537], "isController": false}, {"data": ["The operation lasted too long: It took 833 milliseconds, but should not have lasted longer than 780 milliseconds.", 1, 0.9900990099009901, 0.5847953216374269], "isController": false}, {"data": ["The operation lasted too long: It took 1&nbsp;161 milliseconds, but should not have lasted longer than 780 milliseconds.", 2, 1.9801980198019802, 1.1695906432748537], "isController": false}, {"data": ["The operation lasted too long: It took 823 milliseconds, but should not have lasted longer than 780 milliseconds.", 1, 0.9900990099009901, 0.5847953216374269], "isController": false}, {"data": ["The operation lasted too long: It took 1&nbsp;171 milliseconds, but should not have lasted longer than 780 milliseconds.", 2, 1.9801980198019802, 1.1695906432748537], "isController": false}, {"data": ["The operation lasted too long: It took 844 milliseconds, but should not have lasted longer than 780 milliseconds.", 1, 0.9900990099009901, 0.5847953216374269], "isController": false}, {"data": ["The operation lasted too long: It took 1&nbsp;178 milliseconds, but should not have lasted longer than 780 milliseconds.", 2, 1.9801980198019802, 1.1695906432748537], "isController": false}, {"data": ["The operation lasted too long: It took 818 milliseconds, but should not have lasted longer than 780 milliseconds.", 1, 0.9900990099009901, 0.5847953216374269], "isController": false}, {"data": ["The operation lasted too long: It took 1&nbsp;297 milliseconds, but should not have lasted longer than 780 milliseconds.", 1, 0.9900990099009901, 0.5847953216374269], "isController": false}, {"data": ["The operation lasted too long: It took 812 milliseconds, but should not have lasted longer than 780 milliseconds.", 1, 0.9900990099009901, 0.5847953216374269], "isController": false}, {"data": ["The operation lasted too long: It took 1&nbsp;172 milliseconds, but should not have lasted longer than 780 milliseconds.", 2, 1.9801980198019802, 1.1695906432748537], "isController": false}, {"data": ["The operation lasted too long: It took 1&nbsp;199 milliseconds, but should not have lasted longer than 780 milliseconds.", 1, 0.9900990099009901, 0.5847953216374269], "isController": false}, {"data": ["The operation lasted too long: It took 839 milliseconds, but should not have lasted longer than 780 milliseconds.", 1, 0.9900990099009901, 0.5847953216374269], "isController": false}, {"data": ["The operation lasted too long: It took 1&nbsp;160 milliseconds, but should not have lasted longer than 780 milliseconds.", 1, 0.9900990099009901, 0.5847953216374269], "isController": false}, {"data": ["The operation lasted too long: It took 1&nbsp;151 milliseconds, but should not have lasted longer than 780 milliseconds.", 1, 0.9900990099009901, 0.5847953216374269], "isController": false}, {"data": ["The operation lasted too long: It took 828 milliseconds, but should not have lasted longer than 780 milliseconds.", 1, 0.9900990099009901, 0.5847953216374269], "isController": false}, {"data": ["The operation lasted too long: It took 809 milliseconds, but should not have lasted longer than 780 milliseconds.", 1, 0.9900990099009901, 0.5847953216374269], "isController": false}, {"data": ["The operation lasted too long: It took 1&nbsp;183 milliseconds, but should not have lasted longer than 780 milliseconds.", 2, 1.9801980198019802, 1.1695906432748537], "isController": false}, {"data": ["The operation lasted too long: It took 915 milliseconds, but should not have lasted longer than 780 milliseconds.", 1, 0.9900990099009901, 0.5847953216374269], "isController": false}, {"data": ["The operation lasted too long: It took 1&nbsp;162 milliseconds, but should not have lasted longer than 780 milliseconds.", 1, 0.9900990099009901, 0.5847953216374269], "isController": false}, {"data": ["The operation lasted too long: It took 1&nbsp;136 milliseconds, but should not have lasted longer than 780 milliseconds.", 1, 0.9900990099009901, 0.5847953216374269], "isController": false}, {"data": ["The operation lasted too long: It took 1&nbsp;191 milliseconds, but should not have lasted longer than 780 milliseconds.", 1, 0.9900990099009901, 0.5847953216374269], "isController": false}, {"data": ["The operation lasted too long: It took 817 milliseconds, but should not have lasted longer than 780 milliseconds.", 1, 0.9900990099009901, 0.5847953216374269], "isController": false}, {"data": ["The operation lasted too long: It took 800 milliseconds, but should not have lasted longer than 780 milliseconds.", 1, 0.9900990099009901, 0.5847953216374269], "isController": false}, {"data": ["The operation lasted too long: It took 838 milliseconds, but should not have lasted longer than 780 milliseconds.", 1, 0.9900990099009901, 0.5847953216374269], "isController": false}, {"data": ["The operation lasted too long: It took 1&nbsp;173 milliseconds, but should not have lasted longer than 780 milliseconds.", 2, 1.9801980198019802, 1.1695906432748537], "isController": false}, {"data": ["The operation lasted too long: It took 1&nbsp;179 milliseconds, but should not have lasted longer than 780 milliseconds.", 2, 1.9801980198019802, 1.1695906432748537], "isController": false}, {"data": ["The operation lasted too long: It took 835 milliseconds, but should not have lasted longer than 780 milliseconds.", 1, 0.9900990099009901, 0.5847953216374269], "isController": false}, {"data": ["The operation lasted too long: It took 1&nbsp;169 milliseconds, but should not have lasted longer than 780 milliseconds.", 3, 2.9702970297029703, 1.7543859649122806], "isController": false}, {"data": ["The operation lasted too long: It took 1&nbsp;176 milliseconds, but should not have lasted longer than 780 milliseconds.", 1, 0.9900990099009901, 0.5847953216374269], "isController": false}, {"data": ["Non HTTP response code: java.net.SocketException/Non HTTP response message: Socket closed", 3, 2.9702970297029703, 1.7543859649122806], "isController": false}, {"data": ["The operation lasted too long: It took 1&nbsp;166 milliseconds, but should not have lasted longer than 780 milliseconds.", 5, 4.9504950495049505, 2.9239766081871346], "isController": false}, {"data": ["The operation lasted too long: It took 832 milliseconds, but should not have lasted longer than 780 milliseconds.", 1, 0.9900990099009901, 0.5847953216374269], "isController": false}, {"data": ["The operation lasted too long: It took 1&nbsp;163 milliseconds, but should not have lasted longer than 780 milliseconds.", 1, 0.9900990099009901, 0.5847953216374269], "isController": false}, {"data": ["The operation lasted too long: It took 781 milliseconds, but should not have lasted longer than 780 milliseconds.", 1, 0.9900990099009901, 0.5847953216374269], "isController": false}, {"data": ["The operation lasted too long: It took 813 milliseconds, but should not have lasted longer than 780 milliseconds.", 2, 1.9801980198019802, 1.1695906432748537], "isController": false}, {"data": ["The operation lasted too long: It took 1&nbsp;240 milliseconds, but should not have lasted longer than 780 milliseconds.", 1, 0.9900990099009901, 0.5847953216374269], "isController": false}, {"data": ["The operation lasted too long: It took 819 milliseconds, but should not have lasted longer than 780 milliseconds.", 2, 1.9801980198019802, 1.1695906432748537], "isController": false}, {"data": ["The operation lasted too long: It took 1&nbsp;153 milliseconds, but should not have lasted longer than 780 milliseconds.", 1, 0.9900990099009901, 0.5847953216374269], "isController": false}, {"data": ["The operation lasted too long: It took 1&nbsp;182 milliseconds, but should not have lasted longer than 780 milliseconds.", 1, 0.9900990099009901, 0.5847953216374269], "isController": false}, {"data": ["The operation lasted too long: It took 826 milliseconds, but should not have lasted longer than 780 milliseconds.", 2, 1.9801980198019802, 1.1695906432748537], "isController": false}, {"data": ["The operation lasted too long: It took 1&nbsp;164 milliseconds, but should not have lasted longer than 780 milliseconds.", 1, 0.9900990099009901, 0.5847953216374269], "isController": false}, {"data": ["The operation lasted too long: It took 1&nbsp;241 milliseconds, but should not have lasted longer than 780 milliseconds.", 1, 0.9900990099009901, 0.5847953216374269], "isController": false}, {"data": ["The operation lasted too long: It took 862 milliseconds, but should not have lasted longer than 780 milliseconds.", 1, 0.9900990099009901, 0.5847953216374269], "isController": false}, {"data": ["The operation lasted too long: It took 1&nbsp;175 milliseconds, but should not have lasted longer than 780 milliseconds.", 1, 0.9900990099009901, 0.5847953216374269], "isController": false}, {"data": ["The operation lasted too long: It took 825 milliseconds, but should not have lasted longer than 780 milliseconds.", 2, 1.9801980198019802, 1.1695906432748537], "isController": false}, {"data": ["The operation lasted too long: It took 840 milliseconds, but should not have lasted longer than 780 milliseconds.", 2, 1.9801980198019802, 1.1695906432748537], "isController": false}, {"data": ["The operation lasted too long: It took 1&nbsp;154 milliseconds, but should not have lasted longer than 780 milliseconds.", 1, 0.9900990099009901, 0.5847953216374269], "isController": false}, {"data": ["The operation lasted too long: It took 795 milliseconds, but should not have lasted longer than 780 milliseconds.", 2, 1.9801980198019802, 1.1695906432748537], "isController": false}, {"data": ["The operation lasted too long: It took 1&nbsp;252 milliseconds, but should not have lasted longer than 780 milliseconds.", 1, 0.9900990099009901, 0.5847953216374269], "isController": false}, {"data": ["The operation lasted too long: It took 1&nbsp;139 milliseconds, but should not have lasted longer than 780 milliseconds.", 1, 0.9900990099009901, 0.5847953216374269], "isController": false}, {"data": ["The operation lasted too long: It took 1&nbsp;144 milliseconds, but should not have lasted longer than 780 milliseconds.", 1, 0.9900990099009901, 0.5847953216374269], "isController": false}, {"data": ["The operation lasted too long: It took 814 milliseconds, but should not have lasted longer than 780 milliseconds.", 3, 2.9702970297029703, 1.7543859649122806], "isController": false}, {"data": ["The operation lasted too long: It took 846 milliseconds, but should not have lasted longer than 780 milliseconds.", 1, 0.9900990099009901, 0.5847953216374269], "isController": false}, {"data": ["The operation lasted too long: It took 1&nbsp;155 milliseconds, but should not have lasted longer than 780 milliseconds.", 1, 0.9900990099009901, 0.5847953216374269], "isController": false}, {"data": ["The operation lasted too long: It took 827 milliseconds, but should not have lasted longer than 780 milliseconds.", 2, 1.9801980198019802, 1.1695906432748537], "isController": false}, {"data": ["The operation lasted too long: It took 1&nbsp;142 milliseconds, but should not have lasted longer than 780 milliseconds.", 2, 1.9801980198019802, 1.1695906432748537], "isController": false}, {"data": ["The operation lasted too long: It took 1&nbsp;261 milliseconds, but should not have lasted longer than 780 milliseconds.", 1, 0.9900990099009901, 0.5847953216374269], "isController": false}, {"data": ["The operation lasted too long: It took 837 milliseconds, but should not have lasted longer than 780 milliseconds.", 1, 0.9900990099009901, 0.5847953216374269], "isController": false}, {"data": ["The operation lasted too long: It took 1&nbsp;138 milliseconds, but should not have lasted longer than 780 milliseconds.", 2, 1.9801980198019802, 1.1695906432748537], "isController": false}, {"data": ["The operation lasted too long: It took 1&nbsp;264 milliseconds, but should not have lasted longer than 780 milliseconds.", 1, 0.9900990099009901, 0.5847953216374269], "isController": false}, {"data": ["The operation lasted too long: It took 1&nbsp;148 milliseconds, but should not have lasted longer than 780 milliseconds.", 1, 0.9900990099009901, 0.5847953216374269], "isController": false}, {"data": ["The operation lasted too long: It took 866 milliseconds, but should not have lasted longer than 780 milliseconds.", 1, 0.9900990099009901, 0.5847953216374269], "isController": false}, {"data": ["The operation lasted too long: It took 802 milliseconds, but should not have lasted longer than 780 milliseconds.", 1, 0.9900990099009901, 0.5847953216374269], "isController": false}, {"data": ["The operation lasted too long: It took 1&nbsp;174 milliseconds, but should not have lasted longer than 780 milliseconds.", 3, 2.9702970297029703, 1.7543859649122806], "isController": false}, {"data": ["The operation lasted too long: It took 879 milliseconds, but should not have lasted longer than 780 milliseconds.", 1, 0.9900990099009901, 0.5847953216374269], "isController": false}, {"data": ["The operation lasted too long: It took 786 milliseconds, but should not have lasted longer than 780 milliseconds.", 1, 0.9900990099009901, 0.5847953216374269], "isController": false}, {"data": ["The operation lasted too long: It took 805 milliseconds, but should not have lasted longer than 780 milliseconds.", 1, 0.9900990099009901, 0.5847953216374269], "isController": false}, {"data": ["The operation lasted too long: It took 1&nbsp;177 milliseconds, but should not have lasted longer than 780 milliseconds.", 2, 1.9801980198019802, 1.1695906432748537], "isController": false}]}, function(index, item){
        switch(index){
            case 2:
            case 3:
                item = item.toFixed(2) + '%';
                break;
        }
        return item;
    }, [[1, 1]]);

        // Create top5 errors by sampler
    createTable($("#top5ErrorsBySamplerTable"), {"supportsControllersDiscrimination": false, "overall": {"data": ["Total", 171, 101, "The operation lasted too long: It took 1&nbsp;166 milliseconds, but should not have lasted longer than 780 milliseconds.", 5, "The operation lasted too long: It took 1&nbsp;169 milliseconds, but should not have lasted longer than 780 milliseconds.", 3, "Non HTTP response code: java.net.SocketException/Non HTTP response message: Socket closed", 3, "The operation lasted too long: It took 814 milliseconds, but should not have lasted longer than 780 milliseconds.", 3, "The operation lasted too long: It took 1&nbsp;174 milliseconds, but should not have lasted longer than 780 milliseconds.", 3], "isController": false}, "titles": ["Sample", "#Samples", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors"], "items": [{"data": [], "isController": false}, {"data": ["conf #2 req", 57, 44, "The operation lasted too long: It took 814 milliseconds, but should not have lasted longer than 780 milliseconds.", 3, "The operation lasted too long: It took 782 milliseconds, but should not have lasted longer than 780 milliseconds.", 2, "The operation lasted too long: It took 826 milliseconds, but should not have lasted longer than 780 milliseconds.", 2, "The operation lasted too long: It took 825 milliseconds, but should not have lasted longer than 780 milliseconds.", 2, "The operation lasted too long: It took 840 milliseconds, but should not have lasted longer than 780 milliseconds.", 2], "isController": false}, {"data": ["conf #1 req", 57, 57, "The operation lasted too long: It took 1&nbsp;166 milliseconds, but should not have lasted longer than 780 milliseconds.", 5, "The operation lasted too long: It took 1&nbsp;169 milliseconds, but should not have lasted longer than 780 milliseconds.", 3, "The operation lasted too long: It took 1&nbsp;174 milliseconds, but should not have lasted longer than 780 milliseconds.", 3, "The operation lasted too long: It took 1&nbsp;161 milliseconds, but should not have lasted longer than 780 milliseconds.", 2, "The operation lasted too long: It took 1&nbsp;171 milliseconds, but should not have lasted longer than 780 milliseconds.", 2], "isController": false}]}, function(index, item){
        return item;
    }, [[0, 0]], 0);

});
