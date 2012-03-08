package com.example.fib.common;

import com.example.fib.common.FibRequest;

interface IFibService {
	long fibJ(long n);
	long fibJI(long n);
	long fibN(long n);
	long fibNI(long n);
	
	long fib(in FibRequest request);
}