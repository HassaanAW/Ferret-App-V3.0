package com.talhajavedmukhtar.ferret.PortScanner;

import android.os.AsyncTask;
import android.util.Log;

import com.talhajavedmukhtar.ferret.Util.Tags;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PortScanner extends AsyncTask {
    private String TAG = Tags.makeTag("PortScanner");

    private String ipAddress;

    private int timeout;

    private int minPort;
    private int maxPort;
    private ArrayList<Integer> portsList;

    private int tasksAdded;
    private int tasksDone;

    private ExecutorService executorService;

    private PortScannerInterface portScannerInterface;

    public PortScanner(String ip) {
        ipAddress = ip;

        timeout = 10000; //ms

        //just scanning the first 1024 reserved ports -> changed this to scanning for popular ports only as it was too slow

//        minPort = 0;
//        maxPort = 1025;
        List<Integer> sourceList = Arrays.asList(2121, 11111, 1137, 123, 137, 139, 1443, 1698, 1743, 18181, 1843,
                1923, 19531, 22, 25454, 2869, 32768, 32769, 35518, 35682,
                36866, 3689, 37199, 38576, 41432, 42758, 443, 445, 45363,
                4548, 46355, 46995, 47391, 48569, 49152, 49153, 49154,
                49451, 53, 5353, 548, 554, 56167, 56278, 56789, 56928,
                59815, 6466, 6467, 655, 7676, 7678, 7681, 7685, 7777, 81,
                8181, 8187, 8222, 8443, 88, 8842, 8883, 8886, 8888, 8889,
                911, 9119, 9197, 9295, 9999, 443, 80, 993, 5228, 4070,
                5223, 9543, 1, 2, 4, 5, 6, 7, 9, 11, 13, 15, 17, 18, 19,
                20, 21, 22, 23, 25, 37, 39, 42, 43, 49, 50, 53, 63, 67, 68,
                69, 70, 71, 72, 73, 79, 80, 81, 88, 95, 98, 101, 102, 105,
                106, 107, 109, 110, 111, 113, 115, 117, 119, 123, 137, 138,
                139, 143, 161, 162, 163, 164, 174, 177, 178, 179, 191, 194,
                199, 201, 202, 204, 206, 209, 210, 213, 220, 245, 347, 363,
                369, 370, 372, 389, 427, 434, 435, 443, 444, 445, 464, 465,
                468, 487, 488, 496, 500, 512, 513, 514, 515, 517, 518, 519,
                520, 521, 525, 526, 530, 531, 532, 533, 535, 538, 540, 543,
                544, 546, 547, 548, 554, 556, 563, 565, 587, 610, 611, 612,
                616, 631, 636, 655, 674, 694, 749, 750, 751, 752, 754, 760,
                765, 767, 808, 871, 873, 901, 911, 953, 992, 993, 994, 995,
                1080, 1109, 1127, 1137, 1178, 1236, 1300, 1313, 1433, 1434,
                1443, 1494, 1512, 1524, 1525, 1529, 1645, 1646, 1649, 1698,
                1701, 1718, 1719, 1720, 1743, 1758, 1759, 1789, 1812, 1813,
                1843, 1911, 1923, 1985, 1986, 1997, 2003, 2049, 2053, 2102,
                2103, 2104, 2105, 2121, 2150, 2401, 2430, 2431, 2432, 2433,
                2600, 2601, 2602, 2603, 2604, 2605, 2606, 2809, 2869, 2988,
                3128, 3130, 3306, 3346, 3455, 3689, 4011, 4070, 4321, 4444,
                4548, 4557, 4559, 5002, 5223, 5228, 5232, 5308, 5353, 5354,
                5355, 5432, 5680, 5999, 6000, 6010, 6466, 6467, 6667, 7000,
                7001, 7002, 7003, 7004, 7005, 7006, 7007, 7008, 7009, 7100,
                7666, 7676, 7678, 7681, 7685, 7777, 8008, 8080, 8081, 8181,
                8187, 8222, 8443, 8842, 8883, 8886, 8888, 8889, 9100, 9119,
                9197, 9295, 9359, 9543, 9876, 9999, 10080, 10081, 10082,
                10083, 11111, 11371, 11720, 13720, 13721, 13722, 13724,
                13782, 13783, 18181, 19531, 20011, 20012, 22273, 22289,
                22305, 22321, 24554, 25454, 26000, 26208, 27374, 32768,
                32769, 33434, 35518, 35682, 36866, 37199, 38576, 41432,
                42758, 45363, 46355, 46995, 47391, 48569, 49152, 49153,
                49154, 49451, 56167, 56278, 56789, 56928, 59815, 60177,
                8060,
                60179);
        Set<Integer> targetSet = new HashSet<>(sourceList);

        portsList = new ArrayList<Integer>(targetSet);
        Log.d(TAG, portsList.toString());
        minPort = 0;
        maxPort = portsList.size();
        tasksAdded = 0;
        tasksDone = 0;
    }

    public void setPortScannerInterface(PortScannerInterface pInterface) {
        portScannerInterface = pInterface;
    }

    public void setMinPort(int minPort) {
        this.minPort = minPort;
    }

    public void setMaxPort(int maxPort) {
        this.maxPort = maxPort;
    }

    private void portIsOpen(final String ip, final int port, final int timeout) {
        Socket socket = new Socket();
        InetSocketAddress addr = new InetSocketAddress(ip, port);
        Boolean open = false;
        try {
            socket.connect(addr, timeout);
            socket.close();
            open = true;
        } catch (Exception ex) {
            Log.d(TAG + ".SocketError", ex.getMessage() + " for ip: " + ip + " and port: " + port);
        } finally {
            socket = null;
            addr = null;
            System.gc();

            if (open) {
                portScannerInterface.onPortFound(port);
                Log.d(TAG, "Open port found " + " for ip: " + ip + " and port: " + port);

            }

            synchronized (this) {

                tasksDone += 1;
                Log.d(TAG, Integer.toString(tasksDone));
                Log.d(TAG, "Tasks completed: " + tasksDone);

                if (tasksDone == (maxPort - minPort)) {
                    //Log.d(TAG,"Tasks completed: all");
                    executorService.shutdown();
                    publishProgress("done");
                } else {
                    if (!portsList.isEmpty()) {
                        final int nextPort = portsList.get(0);
                        portsList.remove(0);

                        if (portsList.size() % 10000 == 0) {
                            int progressVal = 7 - (portsList.size() / 10000);
                            publishProgress("running", progressVal);
                        }

                        executorService.execute(new Runnable() {
                            @Override
                            public void run() {
                                portIsOpen(ip, nextPort, timeout);
                            }
                        });
                        tasksAdded++;
                    }
                }
            }

        }
    }

    @Override
    protected Object doInBackground(Object[] objects) {
//        for (int i = minPort; i <= maxPort; i++) {
//            portsList.add(i);
//        }


        int noOfThreads = 40;

        executorService = Executors.newFixedThreadPool(noOfThreads);

        synchronized (this) {
            while (tasksAdded < noOfThreads) {
                if (!portsList.isEmpty()) {
                    final int port = portsList.remove(0);
                    executorService.execute(new Runnable() {
                        @Override
                        public void run() {
                            portIsOpen(ipAddress, port, timeout);
                        }
                    });
                    tasksAdded++;
                }

                if (tasksDone == maxPort) {
                    portScannerInterface.onCompletion();
                }
            }
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Object[] values) {
        super.onProgressUpdate(values);

        String status = (String) values[0];

        if (status.equals("done")) {
            portScannerInterface.onCompletion();
        } else {
            int progressVal = (int) values[1];
            portScannerInterface.on10kDone(progressVal);
        }

    }
}
