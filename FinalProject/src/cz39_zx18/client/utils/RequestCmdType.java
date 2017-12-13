package cz39_zx18.client.utils;

import common.datatype.IRequestCmdType;

/**
 * Implementation of IRequestCmdType for sending packet to request command
 */
public class RequestCmdType implements IRequestCmdType {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8470736333466336155L;
	private Class<?> cmdId;

	/**
	 * Constructor for RequestCmdType
	 * @param cmdId index of the command
	 */
	public RequestCmdType(Class<?> cmdId) {
		this.cmdId = cmdId;
	}

	@Override
	public Class<?> getCmdId() {
		return cmdId;
	}
}
