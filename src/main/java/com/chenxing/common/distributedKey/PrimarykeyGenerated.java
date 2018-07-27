package com.chenxing.common.distributedKey;



import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrimarykeyGenerated {
	// 自增随机数初始值 ---myRandomMin和myRandomMax控制集群情况下主键重复的异常：即：不同server，位于不同的区间。
	private static int myRandom = 0;
	// 上一次访问产生的时间
	private static long lasttime = 0l;
	// 服务器标志,要求是整数，首位补零
	public static String serverIdentity = "";
	private static Lock lock = new ReentrantLock();

	/*********** 优化后的程序 start **********************************/

	// 获取自增随机数
	private static String getMyRandom() {
		String increment = null;
		lock.lock();
		try {
			++myRandom;
			if (myRandom > 99999) {// 自增随机数峰值
				myRandom = 0;// 自增随机数初始值
			}
			increment = String.valueOf(myRandom);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		// 补位
		if (increment.length() == 1) {
			return "0000".concat(increment);
		} else if (increment.length() == 2) {
			return "000".concat(increment);
		} else if (increment.length() == 3) {
			return "00".concat(increment);
		} else if (increment.length() == 4) {
			return "0".concat(increment);
		} else {
			return increment;
		}
	}

	/**
	 * @param localDiff
	 *            true，则在主键中加入服务器唯一标志。false，则不加此标志。
	 * 
	 * */
	public static String generateId(boolean localDiff) {
		checkIfClockBack();
		DateFormat format = new SimpleDateFormat("yyMMddHHmmss");
		StringBuffer sb = new StringBuffer(format.format(new Date().getTime()));
		sb.append(getMyRandom());
		if (localDiff) {
			sb.append(serverIdentity);
		}
		return sb.toString();
	}
	/*********** 优化后的程序 end **********************************/
	/**
	 * check是否时钟回退
	 */
	private static void checkIfClockBack() {
		long time = System.currentTimeMillis();
		if (time < lasttime) {
			throw new RuntimeException("时钟回退异常");
		}
		lasttime = time;
	}
	public static void main(String[] args) {
		long start = System.currentTimeMillis();


		for (int i = 0; i <= 100; i++) {
			generateId(false);
			if (i == 100) {
				System.out.println(generateId(false));
			}

		}
		long end = System.currentTimeMillis();
		System.out.println("cost:" + String.valueOf(end - start));
	}
}
