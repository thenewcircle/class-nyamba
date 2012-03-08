package com.example.fib.common;

import android.os.Parcel;
import android.os.Parcelable;

public class FibRequest implements Parcelable {
	public int type;
	public long n;

	
	public FibRequest(int type, long n) {
		super();
		this.type = type;
		this.n = n;
	}

	public FibRequest(Parcel in) {
		type = in.readInt();
		n = in.readLong();
	}

	@Override
	public void writeToParcel(Parcel out, int flag) {
		out.writeInt(type);
		out.writeLong(n);
	}

	public static final Parcelable.Creator<FibRequest> CREATOR = new Parcelable.Creator<FibRequest>() {
		@Override
		public FibRequest createFromParcel(Parcel source) {
			return new FibRequest(source);
		}

		@Override
		public FibRequest[] newArray(int size) {
			return new FibRequest[size];
		}
	};

	@Override
	public int describeContents() {
		return 0;
	}
}
