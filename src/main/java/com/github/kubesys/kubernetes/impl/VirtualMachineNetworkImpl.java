/**
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package com.github.kubesys.kubernetes.impl;

import com.github.kubesys.kubernetes.api.model.VirtualMachineNetwork;
import com.github.kubesys.kubernetes.api.model.VirtualMachineNetworkList;
import com.github.kubesys.kubernetes.api.model.VirtualMachineNetworkSpec;
import com.github.kubesys.kubernetes.api.model.virtualmachinenetwork.Lifecycle;
import com.github.kubesys.kubernetes.api.model.virtualmachinenetwork.Lifecycle.CreateSwPort;
import com.github.kubesys.kubernetes.api.model.virtualmachinenetwork.Lifecycle.CreateSwitch;
import com.github.kubesys.kubernetes.api.model.virtualmachinenetwork.Lifecycle.DeleteSwPort;
import com.github.kubesys.kubernetes.api.model.virtualmachinenetwork.Lifecycle.DeleteSwitch;

/**
 * @author  wuheng@otcaix.iscas.ac.cn
 * 
 * @version 1.0.0
 * @since   2019/9/1
 **/
public class VirtualMachineNetworkImpl extends AbstractImpl<VirtualMachineNetwork, VirtualMachineNetworkList, VirtualMachineNetworkSpec> {

	@Override
	public VirtualMachineNetwork getModel() {
		return new VirtualMachineNetwork();
	}

	@Override
	public VirtualMachineNetworkSpec getSpec() {
		return new VirtualMachineNetworkSpec();
	}
	
	@Override
	public Object getLifecycle() {
		return new Lifecycle();
	}

	
	@Override
	public VirtualMachineNetworkSpec getSpec(VirtualMachineNetwork r) {
		return r.getSpec();
	}

	/*************************************************
	 * 
	 * Generated by <code>MethodGenerator<code>
	 * 
	 **************************************************/
	public boolean createSwitch(String name, CreateSwitch createSwitch) throws Exception {
		return createSwitch(name, null, createSwitch, null);
	}

	public boolean createSwitch(String name, String nodeName, CreateSwitch createSwitch) throws Exception {
		return createSwitch(name, nodeName, createSwitch, null);
	}

	public boolean createSwitch(String name, CreateSwitch createSwitch, String eventId) throws Exception {
		return createSwitch(name, null, createSwitch, eventId);
	}

	public boolean createSwitch(String name, String nodeName,CreateSwitch createSwitch, String eventId) throws Exception {
		return create(getModel(), createMetadata(name, nodeName, eventId), 
				createSpec(nodeName, createLifecycle(createSwitch)));
	}

	public boolean deleteSwitch(String name, DeleteSwitch deleteSwitch) throws Exception {
		return deleteSwitch(name, deleteSwitch, null);
	}

	public boolean deleteSwitch(String name,DeleteSwitch deleteSwitch, String eventId) throws Exception {
		return delete(name, updateMetadata(name, eventId), deleteSwitch);
	}

	public boolean createSwPort(String name, CreateSwPort createSwPort) throws Exception {
		return createSwPort(name, null, createSwPort, null);
	}

	public boolean createSwPort(String name, String nodeName, CreateSwPort createSwPort) throws Exception {
		return createSwPort(name, nodeName, createSwPort, null);
	}

	public boolean createSwPort(String name, CreateSwPort createSwPort, String eventId) throws Exception {
		return createSwPort(name, null, createSwPort, eventId);
	}

	public boolean createSwPort(String name, String nodeName,CreateSwPort createSwPort, String eventId) throws Exception {
		return create(getModel(), createMetadata(name, nodeName, eventId), 
				createSpec(nodeName, createLifecycle(createSwPort)));
	}

	public boolean deleteSwPort(String name, DeleteSwPort deleteSwPort) throws Exception {
		return deleteSwPort(name, deleteSwPort, null);
	}

	public boolean deleteSwPort(String name,DeleteSwPort deleteSwPort, String eventId) throws Exception {
		return delete(name, updateMetadata(name, eventId), deleteSwPort);
	}
	
}
