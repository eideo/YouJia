package com.arc.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.arc.util.DBUtil;

/**
 * 系统管理中数据库管理的备份与还原功能
 */

@Controller
@Scope("prototype")
public class DataAction extends BaseAction {

	private String outSqlStr;
	// 上传所封装的属性
	private File upload;
	private String uploadFileName;
	private String uploadContentType;

	DBUtil dataSource = new DBUtil();
	String dbUser = dataSource.getDUserName();
	String dbPw = dataSource.getDbPassword();
	String dbName = dataSource.getDbName();
	String dbHostIp = dataSource.getDbHostIp();

	public String backup() {
		BufferedReader br = null;
		try {
			// get the current working path: X:\tomcatPath\bin
			String curWorkPath = System.getProperty("user.dir");
			Runtime rt = Runtime.getRuntime();
			Process child = rt.exec("cmd /c " + curWorkPath + "\\mysqldump -u"
					+ dbUser + " -p" + dbPw + " -h" + dbHostIp + " " + dbName);

			StringBuffer sb = new StringBuffer("");
			String inStr;
			br = new BufferedReader(new InputStreamReader(
					child.getInputStream(), "utf8"));
			while ((inStr = br.readLine()) != null) {
				sb.append(inStr + "\r\n");
			}
			setOutSqlStr(sb.toString());
			br.close();

		} catch (Exception e) {
			message = e.toString();
			setOutSqlStr("备份数据库失败，请联系系统管理员");
			System.out.println(e);
		}
		return "backup";
	}

	public String main() {
		message = "";
		return "main";
	}

	public String reload() {
		OutputStream out = null;
		BufferedReader br = null;
		OutputStreamWriter writer = null;
		BufferedReader bre = null;
		try {
			String curWorkPath = System.getProperty("user.dir");
			String command = "cmd /c " + curWorkPath + "\\mysql -u" + dbUser
					+ " -p" + dbPw + " -h" + dbHostIp + " " + dbName;

			Runtime rt = Runtime.getRuntime();
			Process child = rt.exec(command);
			out = child.getOutputStream();

			String line = null;
			StringBuffer sb = new StringBuffer();
			br = new BufferedReader(new InputStreamReader(new FileInputStream(
					getUpload()), "utf8"));
			while ((line = br.readLine()) != null) {
				sb.append(line + "\r\n");
			}
			String outStr = sb.toString();

			writer = new OutputStreamWriter(out, "utf8");
			writer.write(outStr);
			writer.flush();
			out.close();
			br.close();
			writer.close();

			// 判断是否有错
			bre = new BufferedReader(new InputStreamReader(
					child.getErrorStream()));
			if (bre.readLine() != null) {
				message = "恢复文件出错";
				child.destroy();
			} else {
				message = "恢复成功";
			}
			bre.close();

		} catch (Exception e) {
			message = "恢复失败";
		} finally {// 关闭各个输入流与输出流
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (bre != null) {
				try {
					bre.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return "main";
	}

	public String getOutSqlStr() {
		return outSqlStr;
	}

	public void setOutSqlStr(String outSqlStr) {
		this.outSqlStr = outSqlStr;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

}
