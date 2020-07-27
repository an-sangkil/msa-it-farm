// 화면에 표시되는 페이지수 (엘리멘트수 아님)
const DEFAULT_BLOCK_PAGE_SIZE = 10;

const Pagination = function (currentNo, totalPages, blockPageSize = 10) {
  let BLOCK_PAGE_SIZE, BEGIN, END

  if(blockPageSize || null || undefined || 0 || NaN){
    BLOCK_PAGE_SIZE = DEFAULT_BLOCK_PAGE_SIZE
  } else {
    BLOCK_PAGE_SIZE = blockPageSize
  }

  function pagingBegin(currentNo) {
    BEGIN = Math.max(1, Math.floor(((currentNo - 1) / BLOCK_PAGE_SIZE)) * BLOCK_PAGE_SIZE + 1)
    return BEGIN;
  }

  /**
   * 선택된 페이지에 따른 block page 종료지점
   *
   */
  function pagingEnd(totalPages) {
    END = Math.min(BEGIN + BLOCK_PAGE_SIZE, totalPages)
    if (totalPages >= (BEGIN + BLOCK_PAGE_SIZE)) {
      END = END - 1;
    }
    return this.END;
  }

  function isNextPage(totalPages){
      console.log(totalPages, BEGIN)
      return totalPages > BEGIN;
  }

  function isPrePage(){
    return BEGIN !== 1;
  }

  pagingBegin(currentNo)
  pagingEnd(totalPages)
  let isNextView = isNextPage(totalPages)
  let isPreView  = isPrePage()

  return {
    "begin": BEGIN
    , "end": END
    , "isNext" : isNextView
    , "isPre" : isPreView
  }
}

module.exports = {Pagination, DEFAULT_BLOCK_PAGE_SIZE}
