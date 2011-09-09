package com.j256.ormlite.field.types;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

public class StringBytesTypeTest extends BaseTypeTest {

	private static final String STRING_COLUMN = "string";

	@Test
	public void testStringBytes() throws Exception {
		Class<LocalStringBytes> clazz = LocalStringBytes.class;
		Dao<LocalStringBytes, Object> dao = createDao(clazz, true);
		String val = "string with \u0185";
		LocalStringBytes foo = new LocalStringBytes();
		foo.string = val;
		assertEquals(1, dao.create(foo));
		byte[] valBytes = val.getBytes("Unicode");
		testType(clazz, val, val, valBytes, val, DataType.STRING_BYTES, STRING_COLUMN, false, false, true, false, true,
				false, true, false);
	}

	@Test
	public void testStringBytesFormat() throws Exception {
		Class<LocalStringBytesUtf8> clazz = LocalStringBytesUtf8.class;
		Dao<LocalStringBytesUtf8, Object> dao = createDao(clazz, true);
		String val = "string with \u0185";
		LocalStringBytesUtf8 foo = new LocalStringBytesUtf8();
		foo.string = val;
		assertEquals(1, dao.create(foo));
		testType(clazz, val, val, val.getBytes("UTF-8"), val, DataType.STRING_BYTES, STRING_COLUMN, false, false, true,
				false, true, false, true, false);
	}

	@Test
	public void testStringBytesNull() throws Exception {
		Class<LocalStringBytes> clazz = LocalStringBytes.class;
		Dao<LocalStringBytes, Object> dao = createDao(clazz, true);
		assertEquals(1, dao.create(new LocalStringBytes()));
		testType(clazz, null, null, null, null, DataType.STRING_BYTES, STRING_COLUMN, false, false, true, false, true,
				false, true, false);
	}

	@DatabaseTable(tableName = TABLE_NAME)
	protected static class LocalStringBytes {
		@DatabaseField(columnName = STRING_COLUMN, dataType = DataType.STRING_BYTES)
		String string;
	}

	@DatabaseTable(tableName = TABLE_NAME)
	protected static class LocalStringBytesUtf8 {
		@DatabaseField(columnName = STRING_COLUMN, dataType = DataType.STRING_BYTES, format = "UTF-8")
		String string;
	}
}
