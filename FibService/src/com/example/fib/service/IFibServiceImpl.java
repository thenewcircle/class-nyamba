package com.example.fib.service;

import android.os.RemoteException;

import com.example.fib.FibLib;
import com.example.fib.common.FibRequest;
import com.example.fib.common.IFibService;

public class IFibServiceImpl extends IFibService.Stub {

	@Override
	public long fibJ(long n) throws RemoteException {
		return FibLib.fibJ(n);
	}

	@Override
	public long fibJI(long n) throws RemoteException {
		return FibLib.fibJI(n);
	}

	@Override
	public long fibN(long n) throws RemoteException {
		return FibLib.fibN(n);
	}

	@Override
	public long fibNI(long n) throws RemoteException {
		return FibLib.fibNI(n);
	}

	@Override
	public long fib(FibRequest request) throws RemoteException {
		switch(request.type) {
		case 1: return FibLib.fibJ(request.n);
		case 2: return FibLib.fibJI(request.n);
		case 3: return FibLib.fibN(request.n);
		case 4: return FibLib.fibNI(request.n);
		}
		return 0;
	}


}
