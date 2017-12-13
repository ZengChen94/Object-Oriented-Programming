package cz39_zx18.client.utils;

import common.DataPacketCRAlgoCmd;
import common.datatype.chatroom.ICRInstallCmdType;

/**
 * Implementation of IInstallCmdType for sending packet to install command
 */
public class InstallCmdType implements ICRInstallCmdType {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2429707734687478855L;
	private DataPacketCRAlgoCmd<?> cmd;
	private Class<?> cmdId;

	/**
	 * Constructor for InstallCmdType
	 * @param cmdId index of the command
	 * @param cmd the command to install
	 */
	public InstallCmdType(Class<?> cmdId, DataPacketCRAlgoCmd<?> cmd) {
		this.cmd = cmd;
		this.cmdId = cmdId;
	}

	/**
	 * Get command
	 * @return command The command.
	 */
	@Override
	public DataPacketCRAlgoCmd<?> getCmd() {
		return cmd;
	}

	/**
	 * Get id of command
	 * @return id The id of command.
	 */
	@Override
	public Class<?> getCmdId() {
		return cmdId;
	}
}
