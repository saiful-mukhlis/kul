package com.bmb.app.db;

/**
 * Adalah table sequence yang di gunakan untuk kebutuhan
 * auto increment
 * @author toyib
 *
 */
public class NumberId {
	private String namaTable;
	private long start = 1;
	private long end = 0;
	private int increment = 1;
	private long now=1;
	private String prefix;
	private String format;
	private boolean useFormat = false;
	
	public String getNamaTable() {
		return namaTable;
	}

	public void setNamaTable(String namaTable) {
		this.namaTable = namaTable;
	}

	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}

	public long getEnd() {
		return end;
	}

	public void setEnd(long end) {
		this.end = end;
	}

	public int getIncrement() {
		return increment;
	}

	public void setIncrement(int increment) {
		this.increment = increment;
	}

	public long getNow() {
		return now;
	}

	public void setNow(long now) {
		this.now = now;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public boolean isUseFormat() {
		return useFormat;
	}

	public void setUseFormat(boolean useFormat) {
		this.useFormat = useFormat;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (end ^ (end >>> 32));
		result = prime * result + ((format == null) ? 0 : format.hashCode());
		result = prime * result + increment;
		result = prime * result
				+ ((namaTable == null) ? 0 : namaTable.hashCode());
		result = prime * result + (int) (now ^ (now >>> 32));
		result = prime * result + ((prefix == null) ? 0 : prefix.hashCode());
		result = prime * result + (int) (start ^ (start >>> 32));
		result = prime * result + (useFormat ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NumberId other = (NumberId) obj;
		if (end != other.end)
			return false;
		if (format == null) {
			if (other.format != null)
				return false;
		} else if (!format.equals(other.format))
			return false;
		if (increment != other.increment)
			return false;
		if (namaTable == null) {
			if (other.namaTable != null)
				return false;
		} else if (!namaTable.equals(other.namaTable))
			return false;
		if (now != other.now)
			return false;
		if (prefix == null) {
			if (other.prefix != null)
				return false;
		} else if (!prefix.equals(other.prefix))
			return false;
		if (start != other.start)
			return false;
		if (useFormat != other.useFormat)
			return false;
		return true;
	}

}
