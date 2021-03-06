// WCDriver.java

package com;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WCDriver {
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		Configuration config = new Configuration();
		Job job = Job.getInstance(config, "word count");
		job.setJarByClass(com.WCDriver.class);
		job.setMapperClass(com.WCMap.class);
		job.setReducerClass(com.WCReduce.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
