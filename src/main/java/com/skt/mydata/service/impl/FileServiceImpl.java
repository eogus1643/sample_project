package com.skt.mydata.service.impl;

import com.skt.mydata.service.FileService;

//@Slf4j
//@Service("fileService")
//@RequiredArgsConstructor
public class FileServiceImpl implements FileService{

//	@Value("${filePath.path}")
//    private String filePath;
//
//	private final FileMapper fileMapper;
//
//	@Override
//	public boolean addFile(MultipartFile uploadFile, String gubun) throws Exception {
//		FileVO vo = new FileVO();
//		String serverFileName = UUID.randomUUID().toString();
//		String extsn = uploadFile.getOriginalFilename().substring(uploadFile.getOriginalFilename().lastIndexOf(".") +1 );
//
//		log.debug("=============================");
//		log.debug("getOriginalFilename = " + uploadFile.getOriginalFilename());
//		log.debug("getContentType = " + uploadFile.getContentType());
//		log.debug("getName = " + uploadFile.getName());
//		log.debug("getSize = " + uploadFile.getSize());
//		log.debug("getBytes = " + uploadFile.getBytes());
//		log.debug("=============================");
//
//		File file = new File(filePath , serverFileName + "." + extsn);
//		uploadFile.transferTo(file);
//
//		vo.setRealFileName(uploadFile.getOriginalFilename());
//		vo.setServerFileName(serverFileName + "." + extsn);
//		vo.setExtsn(extsn);
//		vo.setFileSize((int)uploadFile.getSize());
//		vo.setFilePath(filePath);
//		vo.setGubun(gubun);
//		int queryResult = fileMapper.addFile(vo);
//
//		if (queryResult > 0) {return true;}
//		else {return false;}
//
//	}
//
//	@Override
//	public List<FileVO> selectFile(FileVO vo) throws Exception {
//		if (vo.getPage() > 0) {
//			vo.setPage((vo.getPage()*10));
//		} else {
//			vo.setPage(0);
//		}
//		return fileMapper.selectFile(vo);
//	}
//
//	@Override
//	public int deleteFile(FileVO vo) throws Exception {
//		return fileMapper.deleteFile(vo);
//	}
//
//	@Override
//	public FileVO selectDetail(FileVO vo) throws Exception {
//		return fileMapper.selectDetail(vo);
//	}
//
//	@Override
//	public FileVO selectRealFileName(FileVO vo) throws Exception {
//		return fileMapper.selectRealFileName(vo);
//	}

}
