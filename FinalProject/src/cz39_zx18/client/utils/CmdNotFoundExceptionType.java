package cz39_zx18.client.utils;

import common.DataPacketCR;
import common.datatype.chatroom.ICRExceptionStatusType;

/**
 * CmdNotFoundExceptionType defines an exception because cannot find the required command.
 * When a remote user requests a command which the local system don't have actually,
 * local system returns such an exception.  
 */
public class CmdNotFoundExceptionType implements ICRExceptionStatusType {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4878715749602090102L;
	private DataPacketCR<?> originalData;
	private Class<?> cmdId;

	/**
	 * Constructor for CmdNotFoundExceptionType
	 * @param originalData the original data packet causing the exception
	 * @param cmdId  index of the command
	 */
	public CmdNotFoundExceptionType(DataPacketCR<?> originalData, Class<?> cmdId) {
		this.originalData = originalData;
		this.cmdId = cmdId;
	}

	/**
	 * Get original data
	 * @return originalData the original data packet causing the exception
	 */
	@Override
	public DataPacketCR<?> getOriginalData() {
		return originalData;
	}

	/**
	 * Get failure information
	 * @return info Failure information
	 */
	@Override
	public String getFailureInfo() {
		return "Command not found for id " + cmdId;
	}
}
