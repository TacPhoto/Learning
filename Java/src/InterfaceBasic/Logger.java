package InterfaceBasic;

import java.io.IOException;

public interface Logger
{
	public default void log(String text) throws IOException
	{
	}
}
