package com.zking.ssm.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONObject;
import com.baidu.aip.face.AipFace;
import com.baidu.aip.face.FaceVerifyRequest;
import com.baidu.aip.face.MatchRequest;
import com.baidu.aip.util.Base64Util;

//import Decoder.BASE64Decoder;

public class FaceSpot {
	
	//private static final BASE64Decoder decoder = new BASE64Decoder();
	private static final String AppID = "18002380";
	private static final String APIKey = "2orBXDjw7FmL6YxplaO0dYXn";
	private static final String SecretKey = "88t56xPcCAqAZ34fi49SfXObWuKwo2SH";

	static AipFace client = null;
	static {
		client = new AipFace(AppID, APIKey, SecretKey);
		// ��ѡ�������������Ӳ���
		client.setConnectionTimeoutInMillis(2000);
		client.setSocketTimeoutInMillis(60000);
	}
	
	//String file1 = "F:/3.png";
	//String file2 = "F:/2.png";
	//byte[] img1 = FileToByte(new File(file1));
	//byte[] img2 = FileToByte(new File(file1));
	//���������Աȷ��ط���
	//matchFace(img1, img2);
	
	//����һ���û�
	//addUser(img2,"BASE64","22","EasyBuy");
	
	//��ô��û������沿Facial ID
	//getUserFaceList("22","EasyBuy");
	
	//��¼��֧��ʱʹ��
	//searchFace(img2, "EasyBuy","22");
	
	public static void main(String[] args) throws IOException {
		//BASE64Decoder decoder = new BASE64Decoder();
		String file1 = "C:/Users/Admin-Jia/Pictures/Camera Roll/7.jpg";
		byte[] img2 = FileToByte(new File(file1));
		System.out.println(addUser(img2,"BASE64","1","admin"));
//		String file1 = "C:/Users/Admin-Jia/Pictures/Camera Roll/4.jpg";
//		byte[] img2 = FileToByte(new File(file1));
//		System.out.println(searchFace(img2,"admin","1"));
//        System.out.println(addUser(img2,"BASE64","1","admin"));
	}

	/**
	 * �������
	 * 
	 * @return
	 * @throws IOException
	 */
	public static String detectFace(File file, String max_face_num) {
		try {
			return detectFace(FileToByte(file), max_face_num);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * �������
	 * 
	 * @return
	 * @throws IOException
	 */
	public static String detectFace(byte[] arg0, String max_face_num) {
		try {

			HashMap<String, String> options = new HashMap<String, String>();
			options.put("face_field",
					"age,beauty,expression,faceshape,gender,glasses,race,qualities");
			options.put("max_face_num", "2");
			options.put("face_type", "LIVE");

			// ͼƬ����
			String imgStr = Base64Util.encode(arg0);
			String imageType = "BASE64";
			JSONObject res = client.detect(imgStr, imageType, options);
			// System.out.println(res.toString(2));
			return res.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * �����ȶ�
	 * 
	 * @param file1
	 * @param file2
	 * @return
	 */
	public static String matchFace(File file1, File file2) {
		try {
			return matchFace(FileToByte(file1), FileToByte(file2));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * �����ȶ�
	 * 
	 * @param arg0
	 *            ����1
	 * @param arg1
	 *            ����2
	 * @return
	 */
	public static String matchFace(byte[] arg0, byte[] arg1) {
		String imgStr1 = Base64Util.encode(arg0);
		String imgStr2 = Base64Util.encode(arg1);
		MatchRequest req1 = new MatchRequest(imgStr1, "BASE64");
		MatchRequest req2 = new MatchRequest(imgStr2, "BASE64");
		ArrayList<MatchRequest> requests = new ArrayList<MatchRequest>();
		requests.add(req1);
		requests.add(req2);
		JSONObject res = client.match(requests);
		return res.toString();
	}

	/**
	 * ��������
	 * 
	 * @param file
	 * @param groupIdList
	 * @param userId
	 * @return
	 */
	public static String searchFace(File file, String groupIdList, String userId) {
		try {
			return searchFace(FileToByte(file), groupIdList, userId);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ��������
	 * 
	 * @param arg0
	 * @param groupIdList
	 * @return
	 */
	public static String searchFace(byte[] arg0, String groupIdList,
			String userId) {
		String imgStr = Base64Util.encode(arg0);
		System.out.println("imgStr========="+imgStr);
		String imageType = "BASE64";
		HashMap<String, String> options = new HashMap<String, String>();
		options.put("quality_control", "NORMAL");
		options.put("liveness_control", "LOW");
		if (userId != null) {
			options.put("user_id", userId);
		}
		options.put("max_user_num", "1");
		JSONObject res = client.search(imgStr, imageType, groupIdList, options);
		return res.toString(1);
	}
	//Base64����
	public static JSONObject searchFace(String imgStr, String groupIdList,
			String userId) {
		String imageType = "BASE64";
		HashMap<String, String> options = new HashMap<String, String>();
		options.put("quality_control", "NORMAL");
		options.put("liveness_control", "LOW");
		if (userId != null) {
			options.put("user_id", userId);
		}
		options.put("max_user_num", "1");
		JSONObject res = client.search(imgStr, imageType, groupIdList, options);
//		System.out.println(res.toString(2));
		return res;
	}

	/**
	 * �����û�
	 * 
	 * @param file
	 * @param userInfo
	 * @param userId
	 * @param groupId
	 * @return
	 */
	public static String addUser(File file, String userInfo, String userId,
			String groupId) {
		try {
			return addUser(FileToByte(file), userInfo, userId, groupId);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * �����û�
	 * 
	 * @param arg0
	 * @param userInfo
	 * @param userId
	 * @param groupId
	 * @return
	 */
	public static String addUser(byte[] arg0, String userInfo, String userId,String groupId) {
		String imgStr = Base64Util.encode(arg0);
		String imageType = "BASE64";
		HashMap<String, String> options = new HashMap<String, String>();
		options.put("user_info", userInfo);
		options.put("quality_control", "NORMAL");
		options.put("liveness_control", "LOW");

		JSONObject res = client.addUser(imgStr, imageType, groupId, userId,options);
		return res.toString(2);
	}

	public static String updateUser(File file, String userInfo, String userId,
			String groupId) {
		try {
			return updateUser(FileToByte(file), userInfo, userId, groupId);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * �����û�
	 * 
	 * @param arg0
	 * @param userInfo
	 * @param userId
	 * @param groupId
	 * @return
	 */
	public static String updateUser(byte[] arg0, String userInfo,
			String userId, String groupId) {
		String imgStr = Base64Util.encode(arg0);
		String imageType = "BASE64";
		HashMap<String, String> options = new HashMap<String, String>();
		if (userInfo != null) {
			options.put("user_info", userInfo);
		}
		options.put("quality_control", "NORMAL");
		options.put("liveness_control", "LOW");

		JSONObject res = client.updateUser(imgStr, imageType, groupId, userId,
				options);
		return res.toString(2);
	}

	/**
	 * ɾ���û�������Ϣ
	 * 
	 * @param userId
	 * @param groupId
	 * @param faceToken
	 * @return
	 */
	public static String deleteUserFace(String userId, String groupId,
			String faceToken) {
		HashMap<String, String> options = new HashMap<String, String>();
		// ����ɾ��
		JSONObject res = client.faceDelete(userId, groupId, faceToken, options);
		return res.toString();
	}

	/**
	 * ��ѯ�û���Ϣ
	 * 
	 * @param userId
	 * @param groupId
	 * @return
	 */
	public static String searchUserInfo(String userId, String groupId) {
		HashMap<String, String> options = new HashMap<String, String>();
		// �û���Ϣ��ѯ
		JSONObject res = client.getUser(userId, groupId, options);
		return res.toString(2);
	}

	/**
	 * ��ȡ�û������б�
	 * 
	 * @param userId
	 * @param groupId
	 * @return
	 */
	public static String getUserFaceList(String userId, String groupId) {
		HashMap<String, String> options = new HashMap<String, String>();
		// ��ȡ�û������б�
		JSONObject res = client.faceGetlist(userId, groupId, options);
		return res.toString(2);
	}

	/**
	 * ��ȡһ���û�
	 * 
	 * @param groupId
	 * @param returnNum
	 * @return
	 */
	public static String getGroupUsers(String groupId, String returnNum) {
		HashMap<String, String> options = new HashMap<String, String>();
		options.put("start", "0");
		if (returnNum != null) {
			options.put("length", returnNum);
		}
		// ��ȡ�û��б�
		JSONObject res = client.getGroupUsers(groupId, options);
		return res.toString(2);
	}

	/**
	 * ���û�����
	 * 
	 * @param userId
	 * @param srcGroupId
	 * @param dstGroupId
	 * @return
	 */
	public static String userCopy(String userId, String srcGroupId,
			String dstGroupId) {
		HashMap<String, String> options = new HashMap<String, String>();
		options.put("src_group_id", srcGroupId);
		options.put("dst_group_id", dstGroupId);
		// �����û�
		JSONObject res = client.userCopy(userId, options);
		return res.toString(2);
	}

	/**
	 * ɾ���û�
	 * 
	 * @param userId
	 * @param groupId
	 * @return
	 */
	public static String deleteUser(String userId, String groupId) {
		HashMap<String, String> options = new HashMap<String, String>();
		// ����ɾ��
		JSONObject res = client.deleteUser(groupId, userId, options);
		return res.toString();
	}

	/**
	 * ��������Ϣ
	 * 
	 * @param groupId
	 * @return
	 */
	public static String addGroup(String groupId) {
		HashMap<String, String> options = new HashMap<String, String>();
		// �����û���
		JSONObject res = client.groupAdd(groupId, options);
		return res.toString();
	}

	/**
	 * ɾ��
	 * 
	 * @param groupId
	 * @return
	 */
	public static String deleteGroup(String groupId) {
		HashMap<String, String> options = new HashMap<String, String>();
		// �����û���
		JSONObject res = client.groupDelete(groupId, options);
		return res.toString();
	}

	/**
	 * ��ȡ���б�
	 * 
	 * @param length
	 * @return
	 */
	public static String getGroupList(String length) {
		HashMap<String, String> options = new HashMap<String, String>();
		options.put("start", "0");
		options.put("length", length);
		// ���б��ѯ
		JSONObject res = client.getGroupList(options);
		return res.toString();
	}

	/**
	 * ������
	 * 
	 * @param arg0
	 * @return
	 */
	public static String faceverify(byte[] arg0) {
		String imgStr = Base64Util.encode(arg0);
		String imageType = "BASE64";
		FaceVerifyRequest req = new FaceVerifyRequest(imgStr, imageType);
		ArrayList<FaceVerifyRequest> list = new ArrayList<FaceVerifyRequest>();
		list.add(req);
		JSONObject res = client.faceverify(list);
		return res.toString();
	}

	public static byte[] FileToByte(File file) throws IOException {
		// ������תΪ��
		@SuppressWarnings("resource")
		InputStream content = new FileInputStream(file);
		ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
		byte[] buff = new byte[100];
		int rc = 0;
		while ((rc = content.read(buff, 0, 100)) > 0) {
			swapStream.write(buff, 0, rc);
		}
		// ��ö���������
		return swapStream.toByteArray();
	}

}

