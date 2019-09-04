/*
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package com.uit.cloud.kubernetes;

import com.github.kubesys.kubernetes.ExtendedKubernetesClient;
import com.github.kubesys.kubernetes.api.model.virtualmachine.Lifecycle.CreateAndStartVMFromISO;

/**
 * @author wuheng@otcaix.iscas.ac.cn
 * @since  2019/7/15
 *
 * This code is used to manage CustomResource's lifecycle,
 * such as VirtualMachine
 */
public class CreateAndStartSingleDiskWindowsFromISOTest {
	
	
	public static void main(String[] args) throws Exception {
		ExtendedKubernetesClient client = AbstractTest.getClient();
		CreateAndStartVMFromISO createAndStartVMFromISO = get();
		// name
		boolean successful = client.virtualMachines()
				.createAndStartVMFromISO("950646e8c17a49d0b83c1c797811e002", "vm.node30", createAndStartVMFromISO, "123");
		System.out.println(successful);
	}
	
	
	public static CreateAndStartVMFromISO get() throws Exception {
		
		CreateAndStartVMFromISO createAndStartVMFromISO = new CreateAndStartVMFromISO();
		// default value
		createAndStartVMFromISO.setMetadata("uuid=950646e8-c17a-49d0-b83c-1c797811e002");
		createAndStartVMFromISO.setVirt_type("kvm"); 
		// windos use os_variant= "windows" serials. See support serials by running cmd in compute node `osinfo-query os`.
		createAndStartVMFromISO.setOs_variant("win7");
		createAndStartVMFromISO.setNoautoconsole(true); 
		
		// calculationSpecification
		calculationSpecification(createAndStartVMFromISO);  
		
		// Disk and QoS for 1 disk and many disks
		// path /var/lib/libvirt/images/test11 can be get by CreateDiskTest
		// windos use target=hda as boot disk.
		createAndStartVMFromISO.setDisk("/var/lib/libvirt/templates/win7,target=hda,read_bytes_sec=1024000000,write_bytes_sec=1024000000");
		createAndStartVMFromISO.setCdrom("/opt/ISO/cn_windows_7_ultimate_with_sp1_x64_dvd_u_677408.iso");
		
		/*
		 * l2 network example
		 * Parameters:
		 * 	type
		 * 		type of network support values: "bridge", "l2bridge" and "l3bridge"
		 * 	source
		 * 		network source name
		 * 	inbound (optional)
		 * 		inbound bandwidth in KB
		 * 	outbound (optional)
		 * 		outbound bandwidth in KB
		 * 	mac (optional)
		 * 		if no mac, create a random mac
		 * 		Note! Mac address is unique and does not support a value that start with "fe:" (e.g. fe:54:00:05:37:b3)
		 */
//		createAndStartVMFromISO.setNetwork("type=l2bridge,source=br-native,inbound=102400,outbound=102400");
		
		/*
		 * l3 network example
		 * Parameters:
		 * 	type
		 * 		type of network support values: "bridge", "l2bridge" and "l3bridge"
		 * 	source
		 * 		network source name
		 * 	inbound (optional)
		 * 		inbound bandwidth limitation in KB, default is no limitation
		 * 	outbound (optional)
		 * 		outbound bandwidth limitation in KB, default is no limitation
		 * 	mac (optional)
		 * 		if no mac, create a random mac
		 * 		Note! Mac address is unique and does not support a value that start with "fe:" (e.g. fe:54:00:05:37:b3)
		 * 	ip (optional)
		 * 		ip address for l3 network, default is "dynamic" from DHCP
		 * 	switch
		 * 		switch name
		 */
		
		createAndStartVMFromISO.setNetwork("type=l3bridge,source=br-int,model=e1000,inbound=102400,outbound=102400,ip=192.168.5.9,switch=switch");  
		
		// consoleMode amd passowrd
		createAndStartVMFromISO.setGraphics("vnc,listen=0.0.0.0" + getconsolePassword("123456"));
//		createAndStartVMFromISO.setGraphics("spice,listen=0.0.0.0" + getconsolePassword("567890")); 
		
		return createAndStartVMFromISO;
	}


	protected static void calculationSpecification(CreateAndStartVMFromISO createAndStartVMFromISO) {
		createAndStartVMFromISO.setMemory("4096");    
		createAndStartVMFromISO.setVcpus("4" + getCPUSet("1-4,6,8"));
	}
	
	protected static String getCPUSet(String cpuset) {
		return (cpuset == null || cpuset.length() == 0) 
				? "" :",cpuset=" + cpuset;
	}
	
	protected static String getconsolePassword(String pwd) {
		return (pwd == null || pwd.length() == 0) ? "" : ",password=abcdefg";
	}
	
	protected static String getOtherCDROMs() {
		return "--disk /opt/ISO/CentOS-7-x86_64-Minimal-1511.iso,device=cdrom,perms=ro";
	}
	
	protected static String nameToUUID(String name) {
		StringBuffer sb = new StringBuffer();
		sb.append(name.substring(0, 8)).append("-")
				.append(name.substring(8, 12)).append("-")
				.append(name.substring(12, 16)).append("-")
				.append(name.substring(16, 20)).append("-")
				.append(name.substring(20, 32));
		return sb.toString();
	}
	
}