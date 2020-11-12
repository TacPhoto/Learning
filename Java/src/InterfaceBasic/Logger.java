package InterfaceBasic;

import java.io.IOException;

public interface Logger
{
	default void log(String text) throws IOException
	{
	}
}
