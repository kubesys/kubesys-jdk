/**
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package com.github.kubesys.kubernetes.api.specs;

import com.github.kubesys.kubernetes.api.KubeStackSpec;
import com.github.kubesys.kubernetes.api.specs.items.virtualmachinenetwork.Data;
import com.github.kubesys.kubernetes.api.specs.items.virtualmachinenetwork.Lifecycle;

/**
 * @author wuheng@iscas.ac.cn
 * 
 * @version 2.0.0
 * @since   2022.9.28
 **/
public class VirtualMachineNetworkSpec extends KubeStackSpec {


	protected String type;
	
	protected Data data;
	
	protected Lifecycle lifecycle;
	
	public VirtualMachineNetworkSpec() {

	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public Lifecycle getLifecycle() {
		return lifecycle;
	}

	public void setLifecycle(Lifecycle lifecycle) {
		this.lifecycle = lifecycle;
	}

}